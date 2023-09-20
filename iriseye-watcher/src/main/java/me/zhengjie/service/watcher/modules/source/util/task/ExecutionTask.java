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
package me.zhengjie.service.watcher.modules.source.util.task;

import me.zhengjie.service.watcher.modules.source.domain.DataSource;
import me.zhengjie.service.watcher.modules.source.domain.RuleTask;
import me.zhengjie.service.watcher.modules.source.domain.RuleTaskLog;
import me.zhengjie.service.watcher.modules.source.repository.RuleTaskLogRepository;
import me.zhengjie.service.watcher.modules.source.service.RuleTaskService;
import me.zhengjie.utils.RedisUtils;
import me.zhengjie.utils.SpringContextHolder;
import me.zhengjie.utils.StringUtils;
import me.zhengjie.utils.ThrowableUtil;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.Future;

/**
 * 参考人人开源，<a href="https://gitee.com/renrenio/renren-security">...</a>
 * @author /
 * @date 2019-01-07
 */
@Async
public class ExecutionTask extends QuartzJobBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 此处仅供参考，可根据任务执行情况自定义线程池参数
    private final ThreadPoolTaskExecutor executor = SpringContextHolder.getBean("elAsync");

    @Override
    public void executeInternal(JobExecutionContext context) {
        // 获取任务
        RuleTask quartzJob = (RuleTask) context.getMergedJobDataMap().get(RuleTask.TASK_KEY);
        // 获取spring bean
        RuleTaskLogRepository quartzLogRepository = SpringContextHolder.getBean(RuleTaskLogRepository.class);
        RuleTaskService quartzJobService = SpringContextHolder.getBean(RuleTaskService.class);
        RedisUtils redisUtils = SpringContextHolder.getBean(RedisUtils.class);

        String uuid = quartzJob.getUuid();

        RuleTaskLog log = new RuleTaskLog();
        log.setTaskName(quartzJob.getTaskName());
        log.setBeanName(quartzJob.getBeanName());
        log.setMethodName(quartzJob.getMethodName());
        log.setParams(quartzJob.getParams());
        long startTime = System.currentTimeMillis();
        log.setCronExpression(quartzJob.getCronExpression());
        try {
            // 执行任务
            RuleTaskRunnable task = new RuleTaskRunnable(quartzJob, new DataSource());
            Future<?> future = executor.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            if(StringUtils.isNotBlank(uuid)) {
                redisUtils.set(uuid, true);
            }
            // 任务状态
            log.setIsSuccess(true);
            logger.info("任务执行成功，任务名称：" + quartzJob.getTaskName() + ", 执行时间：" + times + "毫秒");
        } catch (Exception e) {
            if(StringUtils.isNotBlank(uuid)) {
                redisUtils.set(uuid, false);
            }
            logger.error("任务执行失败，任务名称：" + quartzJob.getTaskName());
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            // 任务状态 0：成功 1：失败
            log.setIsSuccess(false);
            log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            // 任务如果失败了则暂停
            if(quartzJob.getPauseAfterFailure() != null && quartzJob.getPauseAfterFailure()){
                quartzJob.setIsPause(false);
                //更新状态
                quartzJobService.updateIsPause(quartzJob);
            }
        } finally {
            quartzLogRepository.save(log);
        }
    }

//    private EmailVo taskAlarm(QuartzJob quartzJob, String msg) {
//        EmailVo emailVo = new EmailVo();
//        emailVo.setSubject("定时任务【"+ quartzJob.getJobName() +"】执行失败，请尽快处理！");
//        Map<String, Object> data = new HashMap<>(16);
//        data.put("task", quartzJob);
//        data.put("msg", msg);
//        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
//        Template template = engine.getTemplate("taskAlarm.ftl");
//        emailVo.setContent(template.render(data));
//        List<String> emails = Arrays.asList(quartzJob.getEmail().split("[,，]"));
//        emailVo.setTos(emails);
//        return emailVo;
//    }
}
