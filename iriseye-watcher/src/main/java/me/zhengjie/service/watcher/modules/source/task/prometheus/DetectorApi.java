package me.zhengjie.service.watcher.modules.source.task.prometheus;


/**
 * 用与探测
 *
 * @author wuhao
 * @description: DetectorApi
 * @createTime 2022/05/14 22:54:00
 */


public interface DetectorApi {
    /**
     * detectorApi
     *
     * @param detector
     */
    DetectorResponse detectorApi(DetectorRequest detector);
}
