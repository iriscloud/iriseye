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
package me.zhengjie.service.watcher.modules.util.task;

import lombok.extern.slf4j.Slf4j;
import me.zhengjie.service.watcher.modules.domain.RuleTask;
import me.zhengjie.service.watcher.modules.domain.WatcherSource;
import me.zhengjie.utils.SpringContextHolder;
import me.zhengjie.utils.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 执行定时任务
 *
 * @author /
 */
@Slf4j
public class RuleTaskRunnable implements Callable<Object> {
    private static final String DEFAULT_METHOD = "run";
    private final RuleTask ruleTask;
    private final WatcherSource dataSource;
    private final Object target;
    private final Method method;

    RuleTaskRunnable(RuleTask ruleTask, WatcherSource dataSource)
            throws NoSuchMethodException, SecurityException {
        this.ruleTask = ruleTask;
        this.dataSource = dataSource;
        this.target = SpringContextHolder.getBean(ruleTask.getBeanName());
        String methodStr = StringUtils.isNoneBlank(ruleTask.getMethodName())? ruleTask.getMethodName() : DEFAULT_METHOD;
        this.method = target.getClass().getDeclaredMethod(methodStr, RuleTask.class, WatcherSource.class);
    }

    @Override
    @SuppressWarnings("all")
    public Object call() throws Exception {
        ReflectionUtils.makeAccessible(method);
        method.invoke(target, ruleTask, dataSource);
        return null;
    }
}
