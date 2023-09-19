package me.zhengjie.service.watcher.modules.source.task.detectors.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

/**
 * HttpClientUtil
 *
 * @author letter
 */
public class HttpClientUtil {
    public static final int TIME_OUT = 5000;
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 发送HttpPut方法
     *
     * @param url
     * @param headers
     * @param body
     * @return
     */
    public static HttpResponse httpPut(String url, Map<String, String> headers, String body) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(url);
            httpPut.setConfig(createConfig(TIME_OUT, false));
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpPut.setHeader(key, headers.get(key));
                }
            }
            if (body != null && body.length() > 0) {
                byte[] content = body.getBytes("utf-8");
                ByteArrayEntity entity = new ByteArrayEntity(content);
                httpPut.setEntity(entity);
            }
            HttpResponse httpResponse = httpClient.execute(httpPut);
            return httpResponse;
        } catch (Exception e) {
            LOGGER.error("httpPut Exception. {}", url, e);
        }
        return null;
    }

    /**
     * 发送httpPostMultiPart方法
     *
     * @return
     * @throws IOException
     */
    public static HttpResponse httpPost(String url, Map<String, String> headerMap, String json) {

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(createConfig(TIME_OUT, false));
            HttpResponse response = null;
            if (headerMap != null) {
                for (String key : headerMap.keySet()) {
                    httpPost.setHeader(key, headerMap.get(key));
                }
            }
            httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
            httpPost.setEntity(new StringEntity(json));
            response = httpClient.execute(httpPost);
            return response;
        } catch (IOException e) {
            LOGGER.error("httpPost {}", url, e);
        }
        return null;
    }

    public static String getContent(HttpResponse response) {

        try {
            InputStream inputStream = response.getEntity().getContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, count);
            }
            String content = new String(outputStream.toByteArray());
            return content;
        } catch (IOException e) {
            LOGGER.error("getContent ", e);
        }
        return "";
    }


    /**
     * 发送HttpGet方法
     *
     * @param url
     * @param headers
     * @return
     */
    public static HttpResponse httpGet(String url, Map<String, String> headers) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(createConfig(5000, false));
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpGet.setHeader(key, headers.get(key));
                }
            }

            HttpResponse httpResponse = httpClient.execute(httpGet);
            return httpResponse;
        } catch (Exception e) {
            LOGGER.error("httpGet:{} ", e);
        }

        return null;
    }

    private static RequestConfig createConfig(int timeout, boolean redirectsEnabled) {

        return RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setRedirectsEnabled(redirectsEnabled)
                .build();
    }

    /**
     * 发送HttpDelete方法
     *
     * @param requestUrl
     * @param requestHeaderMap
     * @return
     * @throws IOException
     */
    public static HttpResponse httpDelete(String requestUrl, Map<String, String> requestHeaderMap, String requestBody) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ImHttpDelete imHttpDelete = new ImHttpDelete(requestUrl);
        imHttpDelete.setConfig(createConfig(5000, false));
        if (requestHeaderMap != null) {
            for (String key : requestHeaderMap.keySet()) {
                imHttpDelete.setHeader(key, requestHeaderMap.get(key));
            }
        }
        if (requestBody != null && requestBody.length() > 0) {
            byte[] content = requestBody.getBytes("utf-8");
            ByteArrayEntity entity = new ByteArrayEntity(content);
            imHttpDelete.setEntity(entity);
        }

        return httpClient.execute(imHttpDelete);
    }

    static class ImHttpDelete extends HttpEntityEnclosingRequestBase {
        public final static String METHOD_NAME = "DELETE";

        public ImHttpDelete() {
            super();
        }

        public ImHttpDelete(final URI uri) {
            super();
            setURI(uri);
        }

        /**
         * @throws IllegalArgumentException if the uri is invalid.
         */
        public ImHttpDelete(final String uri) {
            super();
            setURI(URI.create(uri));
        }

        @Override
        public String getMethod() {
            return METHOD_NAME;
        }
    }

    /**
     * 发送httpPostMultiPart方法
     *
     * @return
     * @throws IOException
     */
    public static HttpResponse httpPostMultiPart(String url, Map<String, String> headerMap, HttpEntity httpEntity) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(createConfig(5000, false));
        HttpResponse response = null;
        if (headerMap != null) {
            for (String key : headerMap.keySet()) {
                httpPost.setHeader(key, headerMap.get(key));
            }
        }
        httpPost.setEntity(httpEntity);
        response = httpClient.execute(httpPost);
        return response;
    }


}
