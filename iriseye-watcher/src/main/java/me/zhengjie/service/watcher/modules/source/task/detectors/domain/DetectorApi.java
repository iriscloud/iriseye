package me.zhengjie.service.watcher.modules.source.task.detectors.domain;


import me.zhengjie.service.watcher.modules.source.task.detectors.domain.DetectorRequest;
import me.zhengjie.service.watcher.modules.source.task.detectors.domain.DetectorResponse;

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
