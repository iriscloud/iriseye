package me.zhengjie.service.watcher.modules.manager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "alarm")
public class AlarmManagerConfig {

    /**
     * prometheusUrl
     */
    private String prometheusUrl = "http://10.40.0.253:19090/api/v1/query";

    public String getPrometheusUrl() {
        return prometheusUrl;
    }

    public void setPrometheusUrl(String prometheusUrl) {
        this.prometheusUrl = prometheusUrl;
    }
}
