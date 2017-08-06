package com.xzxy.commons.http;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.xzxy.commons.json.Json;

/**
 * Created by zhangmeng on 17-2-8.
 */
public class HTTP {

    private static final Logger logger = LoggerFactory.getLogger(HTTP.class);

    private static CloseableHttpClient httpClient = null;
    static {
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(2 * 1000)
                .setConnectTimeout(2 * 1000)
                .setSocketTimeout(2 * 1000)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(100);
        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(config)
                .build();
    }


    public static HttpRespEntity post(String url, Map<String, String> map ) throws Exception {
        JsonNode jn = Json.toJson(map);
        return post(url, jn);
    }

    public static HttpRespEntity post(String url, JsonNode json) throws Exception {
        return post(url, Json.stringify(json));
    }

    public static HttpRespEntity post(String url, HttpEntity entity) throws IOException {
        HttpUriRequest req = null ;
        req = RequestBuilder.post(url).setHeader("Content-Type", "application/json;UTF-8").setEntity(entity).build();
        return unify(req);
    }

    public static HttpRespEntity unify(HttpUriRequest req) throws IOException{
        try (CloseableHttpResponse response = httpClient.execute(req)) {
            byte[] bytes = IOUtils.toByteArray(response.getEntity().getContent());
            HttpRespEntity respEntity = new HttpRespEntity(bytes);
            return respEntity;
        }catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }

    public static HttpRespEntity post(String url, String str) throws Exception {
        HttpUriRequest req = null ;
        req = RequestBuilder.post(url).setHeader("Content-Type", "application/json;UTF-8").setEntity(new StringEntity(str, "UTF-8")).build();
        try (CloseableHttpResponse response = httpClient.execute(req)) {
            byte[] bytes = IOUtils.toByteArray(response.getEntity().getContent());
            HttpRespEntity respEntity = new HttpRespEntity(bytes);
            return respEntity;
        }catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }

    public static String post(HttpUriRequest request) throws Exception{
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String resp = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info("resp: " + resp);
            return resp;
        }catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }

    /**
     * 支持表单提交
     * @param url
     * @param entity
     * @return
     * @throws Exception
     */
    public static HttpRespEntity postByForm(String url, HttpEntity entity) throws Exception{
        HttpUriRequest req = null ;
        req = RequestBuilder.post(url).setEntity(entity).build();
        try (CloseableHttpResponse response = httpClient.execute(req)) {
            byte[] bytes = IOUtils.toByteArray(response.getEntity().getContent());
            HttpRespEntity respEntity = new HttpRespEntity(bytes);
            return respEntity;
        }catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }

    public static HttpRespEntity get(String url) throws Exception{
        HttpUriRequest req = RequestBuilder.get(url).build();
        try (CloseableHttpResponse response = httpClient.execute(req)) {
            byte[] bytes = IOUtils.toByteArray(response.getEntity().getContent());
            HttpRespEntity respEntity = new HttpRespEntity(bytes);
            return respEntity;
        } catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }

/*    public static void main(String[] args) throws Exception{
        String str = HTTP.get("https://www.baidu.com").toStr();
        System.out.println(str);
    }*/
}
