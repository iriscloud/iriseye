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
package me.zhengjie.task;

import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.RTask;
import me.zhengjie.service.modules.message.service.MessageService;
import me.zhengjie.service.watcher.modules.domain.RuleTask;
import me.zhengjie.service.watcher.modules.domain.WatcherSource;
import me.zhengjie.service.watcher.modules.task.WatcherTask;
import me.zhengjie.service.watcher.modules.task.prometheus.PrometheusExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * promethus检测
 */
@Slf4j
@RTask
@Service("PrometheusTask")
public class PrometheusTask implements WatcherTask {

    @Resource
    private MessageService messageService;

    @Override
    public void run(RuleTask ruleTask, WatcherSource dataSource) {
        if (!check(dataSource)) {
            return;
        }
        PrometheusExecutor.executePql(dataSource, ruleTask.getParams());
    }

}
