package me.zhengjie.service.watcher.modules.source.task.detectors.domain;


import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmRuleDto;
import me.zhengjie.service.watcher.modules.source.domain.DataSource;
import me.zhengjie.service.watcher.modules.source.domain.QuartzTask;

/**
 * @author wuhao
 * @description: DetectorApi
 * @createTime 2022/05/14 22:54:00
 */


public class DetectorRequest {
    
    private String id;

    private DataSource dataSource;
    
    private QuartzTask quartzTask;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public QuartzTask getQuartzTask() {
        return quartzTask;
    }

    public void setQuartzTask(QuartzTask quartzTask) {
        this.quartzTask = quartzTask;
    }
}
