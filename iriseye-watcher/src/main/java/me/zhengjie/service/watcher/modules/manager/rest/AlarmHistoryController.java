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
import me.zhengjie.service.watcher.modules.manager.service.AlarmHistoryService;
import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmHistoryCriteria;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alarm/history")
@Api(tags = "系统：日志管理")
public class AlarmHistoryController {

    private final AlarmHistoryService alarmHistoryService;

    @Log("导出告警记录")
    @ApiOperation("导出告警记录")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check()")
    public void exportLog(HttpServletResponse response, AlarmHistoryCriteria criteria) throws IOException {
        criteria.setLogType("INFO");
        alarmHistoryService.download(alarmHistoryService.queryAll(criteria), response);
    }

    @Log("导出错误数据")
    @ApiOperation("导出错误数据")
    @GetMapping(value = "/error/download")
    @PreAuthorize("@el.check()")
    public void exportErrorLog(HttpServletResponse response, AlarmHistoryCriteria criteria) throws IOException {
        criteria.setLogType("ERROR");
        alarmHistoryService.download(alarmHistoryService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("告警历史查询")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> queryLog(AlarmHistoryCriteria criteria, Pageable pageable) {
        criteria.setLogType("INFO");
        return new ResponseEntity<>(alarmHistoryService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    @ApiOperation("用户日志查询")
    public ResponseEntity<Object> queryUserLog(AlarmHistoryCriteria criteria, Pageable pageable) {
        criteria.setLogType("INFO");
        criteria.setBlurry(SecurityUtils.getCurrentUsername());
        return new ResponseEntity<>(alarmHistoryService.queryAllByUser(criteria, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/error")
    @ApiOperation("错误日志查询")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> queryErrorLog(AlarmHistoryCriteria criteria, Pageable pageable) {
        criteria.setLogType("ERROR");
        return new ResponseEntity<>(alarmHistoryService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/error/{id}")
    @ApiOperation("日志异常详情查询")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> queryErrorLogDetail(@PathVariable Long id) {
        return new ResponseEntity<>(alarmHistoryService.findByErrDetail(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/del/error")
    @Log("删除所有ERROR日志")
    @ApiOperation("删除所有ERROR日志")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> delAllErrorLog() {
        alarmHistoryService.delAllByError();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/del/info")
    @Log("删除所有INFO日志")
    @ApiOperation("删除所有INFO日志")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> delAllInfoLog() {
        alarmHistoryService.delAllByInfo();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
