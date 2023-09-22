package me.zhengjie.service.watcher.modules.util;

/**
 * SourceType
 * @author wuhao
 * @createTime 2023-08-25
 */
public enum WatcherSourceType {
    Prometheus("Prometheus"), ClickHouse("ClickHouse"), MySQL("MySQL");
    public String name;

    WatcherSourceType(String name) {
        this.name = name;
    }
}
