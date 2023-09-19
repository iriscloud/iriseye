package me.zhengjie.utils.http;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import static org.asynchttpclient.Dsl.asyncHttpClient;

/**
 * @author lihongbo
 * @create 2020-04-27 5:25 下午
 **/
public class HttpClientManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientManager.class);
    private SslContext sslCtx = null;

    private HttpClientConfig httpClientConfig = null;

    private static final Cache<String, AsyncHttpClient> CLIENT_MAP = Caffeine.newBuilder().expireAfterAccess(15,
            TimeUnit.MINUTES).removalListener((RemovalListener<String, AsyncHttpClient>) (key, value, cause) -> {
        try {
            LOGGER.debug("{} remove AsyncHttpClient cause:{}", key, cause.name());
            if (value != null) {
                value.close();
            }
        } catch (Exception e) {
            LOGGER.warn("AsyncHttpClient {} removed ,close error", key, e);
        }
    }).build();

    private HttpClientManager() {
        httpClientConfig = new HttpClientConfig();
        initSsl();
        initConfig();
    }

    AsyncHttpClientConfig asyncHttpClientConf = null;

    private void initConfig() {
        asyncHttpClientConf = new DefaultAsyncHttpClientConfig.Builder().setCompressionEnforced(true)
                /** 变更原因: 实际上我们一个池对众多的 http 地址, 每个都启用 keepalive 并没啥意义. 复用不上.
                 keepalived
                 */
                .setKeepAlive(true)
                .setRequestTimeout(5000)
                .setConnectTimeout(5000)
                .setReadTimeout(5000)
                // 空闲连接超时时间
                .setPooledConnectionIdleTimeout(20000)
                .setIoThreadsCount(4)
                .setMaxRequestRetry(3).setSslContext(sslCtx).build();
    }

    private void initSsl() {
        try {

            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String authType) {
                    //trust any client
                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs,
                                               String string) {
                    //trust any server
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sslCtx = SslContextBuilder.forClient().trustManager(tm).build();
        } catch (Exception e) {
            LOGGER.error("[ssl error]", e);
        }
    }


    private static class HttpClientManagerHolder {

        static final HttpClientManager HOLDER = new HttpClientManager();
    }

    public static HttpClientManager getInstance() {
        return HttpClientManagerHolder.HOLDER;
    }

    public AsyncHttpClient getHttpClient(String poolName) {

        if (httpClientConfig.isHttpClientPoolSingleton()) {
            poolName = httpClientConfig.getHttpClientPoolName();
        }

        return CLIENT_MAP.get(poolName, k -> asyncHttpClient(asyncHttpClientConf));

    }


}
