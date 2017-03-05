package com.mateam.mqtt.connector.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.StringTokenizer;

public class LinuxUtils {

	private static final Logger log = LoggerFactory.getLogger(LinuxUtils.class);

	public static final String getHostMacAddress() throws Exception {
		String macAddress = null;

		try {
			InetAddress ip = InetAddress.getLocalHost();

			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			String gatewayIP = getDefaultGatewayAddress();

			if (network == null) {
				Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
				while (nis.hasMoreElements()) {
					NetworkInterface ni = nis.nextElement();

					// Ping to default gateway
					String ping = "ping -I " + ni.getName() + " -q -w 1 -c 1 " + gatewayIP;
					boolean reachable = verifyCommand(ping);

					if (reachable) {

						byte[] niMac = ni.getHardwareAddress();
						if (niMac != null && niMac.length > 0) {
							network = ni;
							break;
						}

					}

				}
			}

			if (network != null) {
				byte[] mac = network.getHardwareAddress();
				if (mac != null) {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
					}
					macAddress = sb.toString();
				}
			}
		} catch (Exception e) {
			log.error("Error getting MAC address", e);
			throw new Exception("Error getting MAC address", e);
		}

		return macAddress;
	}

	public static final String getDefaultGatewayAddress() throws Exception {
		/*String gatewayIP = executeCommand("ip r");

		String[] lines = gatewayIP.split("\n");
		for (String line : lines) {
			if (line.startsWith("default")) {
				StringTokenizer st = new StringTokenizer(line);
				st.nextToken();
				st.nextToken();
				gatewayIP = st.nextToken(); // get 3rd column
			}
		}

		return gatewayIP;*/
		return "127.0.0.1:1883";
	}

	public static final String executeCommand(String command) throws Exception {
		StringBuffer output = new StringBuffer();

		Process p;
		p = Runtime.getRuntime().exec(command);
		p.waitFor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

		String line = "";
		while ((line = reader.readLine()) != null) {
			output.append(line + "\n");
		}

		return output.toString();
	}

	public static final boolean verifyCommand(String command) throws Exception {
		Process p;
		p = Runtime.getRuntime().exec(command);
		int returnVal = p.waitFor();

		return returnVal == 0;
	}

}
