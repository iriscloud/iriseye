/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.service.watcher.modules.source.task;

import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.RTask;
import org.springframework.stereotype.Service;

/**
 * promethus检测
 * 
 */
@Slf4j
@RTask
@Service("PrometheusTask")
public class PrometheusTask implements WatcherTask{

    @Override
    public void run() {
        log.info("run 执行成功");
    }

    @Override
    public void run(String args) {
        log.info("run 执行成功. {}", args);
    }
}
