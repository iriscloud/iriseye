package me.zhengjie.service.watcher.modules.util;

/**
 * SourceType
 *
 * @author wuhao
 * @createTime 2023-08-25
 */
public enum DataSourceType {
    PQL("PQL", "PQL", "jdbc:clickhouse://127.0.0.1:3306"),
    SQL("SQL", "SQL", "http://127.0.0.1:1300/api/v1/query"),
    EQL("EQL", "EQL", "jdbc:clickhouse://127.0.0.1:3306");
    public String name;

    public String subName;
    public String urlDesc;

    DataSourceType(String name, String subName, String urlDesc) {

        this.name = name;
        this.subName = subName;
        this.urlDesc = urlDesc;
    }
}
