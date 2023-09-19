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
package me.zhengjie.service.watcher.modules.manager.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.service.watcher.modules.manager.domain.AlarmRule;
import me.zhengjie.service.watcher.modules.manager.service.AlarmRuleService;
import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmRuleQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2019-09-05
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "告警：规则管理")
@RequestMapping("/api/alarm/rule")
public class AlarmRuleController {

    private final AlarmRuleService alarmRuleService;
    private static final String ENTITY_NAME = "job";

    @PostConstruct
    public void init() {
        System.out.println("init");
    }

    @ApiOperation("导出规则")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('alarmRules:list')")
    public void exportJob(HttpServletResponse response, AlarmRuleQueryCriteria criteria) throws IOException {
        alarmRuleService.download(alarmRuleService.queryAll(criteria), response);
    }

    @ApiOperation("查询规则")
    @GetMapping
    @PreAuthorize("@el.check('alarmRules:list')")
    public ResponseEntity<Object> queryJob(AlarmRuleQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(alarmRuleService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("新增规则")
    @ApiOperation("新增规则")
    @PostMapping
    @PreAuthorize("@el.check('job:add')")
    public ResponseEntity<Object> createJob(@Validated @RequestBody AlarmRule resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        alarmRuleService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改规则")
    @ApiOperation("修改规则")
    @PutMapping
    @PreAuthorize("@el.check('job:edit')")
    public ResponseEntity<Object> updateJob(@Validated(AlarmRule.Update.class) @RequestBody AlarmRule resources) {
        alarmRuleService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除规则")
    @ApiOperation("删除规则")
    @DeleteMapping
    @PreAuthorize("@el.check('job:del')")
    public ResponseEntity<Object> deleteJob(@RequestBody Set<Long> ids) {
        // 验证是否被用户关联
        alarmRuleService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}