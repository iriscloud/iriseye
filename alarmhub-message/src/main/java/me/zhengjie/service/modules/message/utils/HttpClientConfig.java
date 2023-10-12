package me.zhengjie.service.modules.message.utils;

/**
 * @author lihongbo
 * @create 2020-04-27 4:39 下午
 **/
public class HttpClientConfig {
    private boolean sslCtx = false;
    private boolean httpClientPoolSingleton = true;
    private String httpClientPoolName = "http_t_pool";

    public boolean isSslCtx() {
        return sslCtx;
    }

    public HttpClientConfig setSslCtx(boolean sslCtx) {
        this.sslCtx = sslCtx;
        return this;
    }

    public boolean isHttpClientPoolSingleton() {
        return httpClientPoolSingleton;
    }

    public HttpClientConfig setHttpClientPoolSingleton(boolean httpClientPoolSingleton) {
        this.httpClientPoolSingleton = httpClientPoolSingleton;
        return this;
    }

    public String getHttpClientPoolName() {
        return httpClientPoolName;
    }

    public HttpClientConfig setHttpClientPoolName(String httpClientPoolName) {
        this.httpClientPoolName = httpClientPoolName;
        return this;
    }
}
