package me.zhengjie.service.watcher.modules.task;

import me.zhengjie.service.watcher.modules.domain.RuleTask;
import me.zhengjie.service.watcher.modules.domain.WatcherSource;
import me.zhengjie.utils.StringUtils;

/**
 * WatcherTask
 *
 * @author wuhao
 * @createTime 2023-09-15
 */
public interface WatcherTask {

    /**
     * run
     *
     * @param ruleTask
     * @param dataSource
     */
    void run(RuleTask ruleTask, WatcherSource dataSource);

    /**
     * check
     *
     * @param dataSource
     * @return
     */
    default boolean check(WatcherSource dataSource) {
        if (dataSource == null || StringUtils.isBlank(dataSource.getUrl())) {
            return false;
        }
        return true;
    }

}
