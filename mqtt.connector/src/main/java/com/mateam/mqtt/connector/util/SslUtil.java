package com.mateam.mqtt.connector.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class SslUtil
{
	
	private static final Logger logger = LoggerFactory.getLogger(SslUtil.class);
	
	/**
	 * Creates an SSL/TLS socket factory with the given CA certificate file and protocol version.
	 */
	public static SSLSocketFactory getSocketFactory(final String caCrtFile, final String protocolVersion) throws Exception
	{
		try
		{
			Security.addProvider(new BouncyCastleProvider());
			
			// Create SSL/TLS socket factory
			final SSLContext context = SSLContext.getInstance(protocolVersion);
			
			context.init(
					null, 
					SslUtil.getTrustManagerFactory(caCrtFile).getTrustManagers(), null);
	
			return context.getSocketFactory();
		}
		catch (Exception e)
		{
			logger.error("Cannot create SSL/TLS connection", e);
			throw new Exception("Cannot create SSL/TLS connection", e);
		}
	}
	
	/**
     * Creates a trust manager factory.
     */
	public static TrustManagerFactory getTrustManagerFactory(final String caCertificateFile) 
			throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException
	{
		// Load CA certificate
		final X509Certificate caCertificate = (X509Certificate) SslUtil.loadX509Certificate(caCertificateFile);
		
		// CA certificate is used to authenticate server
		final KeyStore caKs = KeyStore.getInstance(KeyStore.getDefaultType());
		caKs.load(null, null);
		caKs.setCertificateEntry("ca-certificate", caCertificate);
		
		final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(caKs);
		
		return tmf;
	}
	
	/**
     * Loads an X509 certificate from the given location.
     */
    public static X509Certificate loadX509Certificate(final String certificateFile) throws IOException, CertificateException 
    {
        final CertificateFactory cf = CertificateFactory.getInstance("X.509");
        final InputStream inputStream = SslUtil.class.getClassLoader().getResourceAsStream(certificateFile);
        final X509Certificate certificate = (X509Certificate) cf.generateCertificate(inputStream);
        inputStream.close();
        return certificate;
    }
}
