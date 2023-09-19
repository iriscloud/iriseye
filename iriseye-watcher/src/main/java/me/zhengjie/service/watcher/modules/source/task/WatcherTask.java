package me.zhengjie.service.watcher.modules.source.task;

/**
 * WatcherTask
 * @author wuhao
 * @createTime 2023-09-15
 */
public interface WatcherTask {
    /**
     * run
     */
    void run();

    /**
     * run
     * @param args
     */
    void run(String args);
}
