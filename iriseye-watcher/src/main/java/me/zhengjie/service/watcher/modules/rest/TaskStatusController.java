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
import me.zhengjie.service.watcher.modules.domain.TaskStatus;
import me.zhengjie.service.watcher.modules.service.TaskStatusService;
import me.zhengjie.service.watcher.modules.service.dto.TaskStatusQueryCriteria;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tstatus")
@Api(tags = "告警:告警状态")
public class TaskStatusController {

    private static final String ENTITY_NAME = "taskStatus";
    private final TaskStatusService taskStatusService;

    @ApiOperation("查询定时任务")
    @GetMapping
    @PreAuthorize("@el.check('tasks:list')")
    public ResponseEntity<PageResult<TaskStatus>> queryTaskStatus(TaskStatusQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(taskStatusService.queryAll(criteria, pageable), HttpStatus.OK);
    }


}
