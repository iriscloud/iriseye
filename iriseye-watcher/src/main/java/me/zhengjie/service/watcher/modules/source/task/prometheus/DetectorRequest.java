package me.zhengjie.service.watcher.modules.source.task.prometheus;


import me.zhengjie.service.watcher.modules.source.domain.WatcherSource;
import me.zhengjie.service.watcher.modules.source.domain.RuleTask;

/**
 * @author wuhao
 * @description: DetectorApi
 * @createTime 2022/05/14 22:54:00
 */


public class DetectorRequest {
    
    private String id;

    private WatcherSource dataSource;
    
    private RuleTask quartzTask;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WatcherSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(WatcherSource dataSource) {
        this.dataSource = dataSource;
    }

    public RuleTask getQuartzTask() {
        return quartzTask;
    }

    public void setQuartzTask(RuleTask quartzTask) {
        this.quartzTask = quartzTask;
    }
}