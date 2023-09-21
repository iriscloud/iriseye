package me.zhengjie.service.watcher.modules.source.task;

import me.zhengjie.service.watcher.modules.source.domain.WatcherSource;
import me.zhengjie.service.watcher.modules.source.domain.RuleTask;

/**
 * WatcherTask
 *
 * @author wuhao
 * @createTime 2023-09-15
 */
public interface WatcherTask {
    /**
     * run
     */
    void run(RuleTask ruleTask, WatcherSource dataSource);

}
