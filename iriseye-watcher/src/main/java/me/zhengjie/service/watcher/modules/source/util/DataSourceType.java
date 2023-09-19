package me.zhengjie.service.watcher.modules.source.util;

/**
 * SourceType
 * @author wuhao
 * @createTime 2023-08-25
 */
public enum DataSourceType {
    Prometheus("Prometheus"), ClickHouse("ClickHouse"), MySQL("MySQL");
    public String name;

    DataSourceType(String name) {
        this.name = name;
    }
}
