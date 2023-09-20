package me.zhengjie.service.watcher.modules.message.utils;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * HttpClientUtils
 * @author wuhao
 */
public class HttpClientUtils {
    public static final String DEFAULT_POOL_NAME = "Alarm_Proxy";
    public static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    public static void sendHttpRequest(String url, String body) {

        try {
            LOGGER.info("send begin url:{} body:{}", url, body);
            long beginTime = System.currentTimeMillis();
            RequestBuilder requestBuilder = new RequestBuilder("POST").setUrl(url);
            requestBuilder.setBody(body.getBytes(StandardCharsets.UTF_8));
            requestBuilder.setHeader("Content-Type", "application/json;charset=UTF-8");
            AsyncHttpClient httpClient = HttpClientManager.getInstance().getHttpClient(DEFAULT_POOL_NAME);
            httpClient.executeRequest(requestBuilder.build(), new AsyncCompletionHandler<Response>() {
                @Override
                public Response onCompleted(Response resp) {
                    long handleTime = System.currentTimeMillis() - beginTime;
                    int code = resp.getStatusCode();
                    LOGGER.info("send success url:{} code:{} time:{} onCompleted:{}", url, code, handleTime, resp.getResponseBody());
                    return resp;
                }

                @Override
                public void onThrowable(Throwable t) {

                    long handleTime = System.currentTimeMillis() - beginTime;
                    LOGGER.error("send error url:{} time:{} error:{}", url, handleTime, t.getMessage());
                }

            });

        } catch (Exception e) {
            LOGGER.error("send error, url:{} body:{} ", url, body);
        }
    }
}
