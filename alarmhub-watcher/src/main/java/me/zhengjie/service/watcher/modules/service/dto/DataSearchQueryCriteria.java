package me.zhengjie.service.watcher.modules.service.dto;

/**
 * DataSearch
 *
 * @author wuhao
 * @description: DataSearch
 * @createTime 2023/10/13 17:36:00
 */

public class DataSearchQueryCriteria {
    private String args;
    private String sourceName;

    private String taskName;

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
