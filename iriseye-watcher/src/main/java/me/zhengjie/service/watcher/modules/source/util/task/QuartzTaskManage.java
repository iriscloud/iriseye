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

import lombok.extern.slf4j.Slf4j;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.service.watcher.modules.source.domain.QuartzTask;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Slf4j
@Component
public class QuartzTaskManage {

    private static final String JOB_NAME = "TASK_";

    @Resource
    private Scheduler scheduler;

    public void addJob(QuartzTask quartzTask){
        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ExecutionTask.class).
                    withIdentity(JOB_NAME + quartzTask.getId()).build();

            //通过触发器名和cron 表达式创建 Trigger
            Trigger cronTrigger = newTrigger()
                    .withIdentity(JOB_NAME + quartzTask.getId())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(quartzTask.getCronExpression()))
                    .build();

            cronTrigger.getJobDataMap().put(QuartzTask.TASK_KEY, quartzTask);

            //重置启动时间
            ((CronTriggerImpl)cronTrigger).setStartTime(new Date());

            //执行定时任务
            scheduler.scheduleJob(jobDetail,cronTrigger);

            // 暂停任务
            if (quartzTask.getIsPause()) {
                pauseJob(quartzTask);
            }
        } catch (Exception e){
            log.error("创建定时任务失败", e);
            throw new BadRequestException("创建定时任务失败");
        }
    }

    /**
     * 更新job cron表达式
     * @param quartzJob /
     */
    public void updateJobCron(QuartzTask quartzJob){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if(trigger == null){
                addJob(quartzJob);
                trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            }
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置启动时间
            ((CronTriggerImpl)trigger).setStartTime(new Date());
            trigger.getJobDataMap().put(QuartzTask.TASK_KEY,quartzJob);

            scheduler.rescheduleJob(triggerKey, trigger);
            // 暂停任务
            if (quartzJob.getIsPause()) {
                pauseJob(quartzJob);
            }
        } catch (Exception e){
            log.error("更新定时任务失败", e);
            throw new BadRequestException("更新定时任务失败");
        }

    }

    /**
     * 删除一个job
     * @param quartzJob /
     */
    public void deleteJob(QuartzTask quartzJob){
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (Exception e){
            log.error("删除定时任务失败", e);
            throw new BadRequestException("删除定时任务失败");
        }
    }

    /**
     * 恢复一个job
     * @param quartzJob /
     */
    public void resumeJob(QuartzTask quartzJob){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if(trigger == null) {
                addJob(quartzJob);
            }
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.resumeJob(jobKey);
        } catch (Exception e){
            log.error("恢复定时任务失败", e);
            throw new BadRequestException("恢复定时任务失败");
        }
    }

    /**
     * 立即执行job
     * @param quartzJob /
     */
    public void runJobNow(QuartzTask quartzJob){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if(trigger == null) {
                addJob(quartzJob);
            }
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(QuartzTask.TASK_KEY, quartzJob);
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.triggerJob(jobKey,dataMap);
        } catch (Exception e){
            log.error("定时任务执行失败", e);
            throw new BadRequestException("定时任务执行失败");
        }
    }

    /**
     * 暂停一个job
     * @param quartzJob /
     */
    public void pauseJob(QuartzTask quartzJob){
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.pauseJob(jobKey);
        } catch (Exception e){
            log.error("定时任务暂停失败", e);
            throw new BadRequestException("定时任务暂停失败");
        }
    }
}
