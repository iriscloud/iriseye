package me.zhengjie.service.watcher.modules.type;

/**
 * @author wuhao
 * @createTime 2023-09-15
 */
public enum SourceType {
    Prometheus("Prometheus", "PQL"),
    MySQL("MySQL", "SQL"),
    ClickHouse("ClickHouse", "SQL"),
    Cassandra("Cassandra", "SQL"),
    Elasticsearch("Elasticsearch", "EQL"),
    Redis("Redis", "RQL"),
    Zookeeper("Zookeeper", "ZQL");
    private String name;
    private String op;

    SourceType(String name, String op) {
        this.name = name;
        this.op = op;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
