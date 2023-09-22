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
package me.zhengjie.service.watcher.modules.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.service.watcher.modules.domain.RuleTask;
import me.zhengjie.service.watcher.modules.domain.RuleTaskLog;
import me.zhengjie.service.watcher.modules.service.RuleTaskService;
import me.zhengjie.service.watcher.modules.service.dto.RuleTaskQueryCriteria;
import me.zhengjie.service.watcher.modules.service.dto.RuleTaskDto;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.SpringContextHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
@Api(tags = "告警:告警管理")
public class RuleTaskController {

    private static final String ENTITY_NAME = "quartzTask";
    private final RuleTaskService quartzTaskService;

    @ApiOperation("查询定时任务")
    @GetMapping
    @PreAuthorize("@el.check('tasks:list')")
    public ResponseEntity<PageResult<RuleTask>> queryQuartzJob(RuleTaskQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(quartzTaskService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @ApiOperation("查询任务类型")
    @GetMapping(value = "/names")
    @PreAuthorize("@el.check('tasks:list')")
    public ResponseEntity<PageResult<RuleTaskDto>> queryQuartzRTask(RuleTaskQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<PageResult<RuleTaskDto>>(quartzTaskService.getAllRTasks(), HttpStatus.OK);
    }

    @ApiOperation("导出任务数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('tasks:list')")
    public void exportQuartzJob(HttpServletResponse response, RuleTaskQueryCriteria criteria) throws IOException {
        quartzTaskService.download(quartzTaskService.queryAll(criteria), response);
    }

    @ApiOperation("导出日志数据")
    @GetMapping(value = "/logs/download")
    @PreAuthorize("@el.check('tasks:list')")
    public void exportQuartzJobLog(HttpServletResponse response, RuleTaskQueryCriteria criteria) throws IOException {
        quartzTaskService.downloadLog(quartzTaskService.queryAllLog(criteria), response);
    }

    @ApiOperation("查询任务执行日志")
    @GetMapping(value = "/logs")
    @PreAuthorize("@el.check('tasks:list')")
    public ResponseEntity<PageResult<RuleTaskLog>> queryQuartzJobLog(RuleTaskQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(quartzTaskService.queryAllLog(criteria,pageable), HttpStatus.OK);
    }

    @Log("新增定时任务")
    @ApiOperation("新增定时任务")
    @PostMapping
    @PreAuthorize("@el.check('tasks:add')")
    public ResponseEntity<Object> createQuartzJob(@Validated @RequestBody RuleTask resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        // 验证Bean是不是合法的，合法的定时任务 Bean 需要用 @Service 定义
        checkBean(resources.getBeanName());
        quartzTaskService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改定时任务")
    @ApiOperation("修改定时任务")
    @PutMapping
    @PreAuthorize("@el.check('tasks:edit')")
    public ResponseEntity<Object> updateQuartzJob(@Validated(RuleTask.Update.class) @RequestBody RuleTask resources){
        // 验证Bean是不是合法的，合法的定时任务 Bean 需要用 @Service 定义
        checkBean(resources.getBeanName());
        quartzTaskService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("更改定时任务状态")
    @ApiOperation("更改定时任务状态")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@el.check('tasks:edit')")
    public ResponseEntity<Object> updateQuartzJobStatus(@PathVariable Long id){
        quartzTaskService.updateIsPause(quartzTaskService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("执行定时任务")
    @ApiOperation("执行定时任务")
    @PutMapping(value = "/exec/{id}")
    @PreAuthorize("@el.check('tasks:edit')")
    public ResponseEntity<Object> executionQuartzJob(@PathVariable Long id){
        quartzTaskService.execution(quartzTaskService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除定时任务")
    @ApiOperation("删除定时任务")
    @DeleteMapping
    @PreAuthorize("@el.check('tasks:del')")
    public ResponseEntity<Object> deleteQuartzJob(@RequestBody Set<Long> ids){
        quartzTaskService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void checkBean(String beanName){
        if(!SpringContextHolder.getAllRTask().contains(beanName)){
            throw new BadRequestException("非法的 Bean，请重新输入！");
        }
    }
}
