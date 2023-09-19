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
package me.zhengjie.service.watcher.modules.manager.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.service.watcher.modules.manager.domain.AlarmHistory;
import me.zhengjie.service.watcher.modules.manager.repository.AlarmHistoryRepository;
import me.zhengjie.service.watcher.modules.manager.service.AlarmHistoryService;
import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmHistoryCriteria;
import me.zhengjie.service.watcher.modules.manager.service.mapstruct.AlarmHistoryMapper;
import me.zhengjie.utils.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
@Service
@RequiredArgsConstructor
public class AlarmHistoryServiceImpl implements AlarmHistoryService {
    private final AlarmHistoryRepository alarmHistoryRepository;
    private final AlarmHistoryMapper alarmHistoryMapper;

    @Override
    public Object queryAll(AlarmHistoryCriteria criteria, Pageable pageable) {
        Page<AlarmHistory> page = alarmHistoryRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)), pageable);
        String status = "ERROR";
        if (status.equals(criteria.getLogType())) {
            return PageUtil.toPage(page.map(alarmHistoryMapper::toDto));
        }
        return page;
    }

    @Override
    public List<AlarmHistory> queryAll(AlarmHistoryCriteria criteria) {
        return alarmHistoryRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)));
    }

    @Override
    public Object queryAllByUser(AlarmHistoryCriteria criteria, Pageable pageable) {
        Page<AlarmHistory> page = alarmHistoryRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)), pageable);
        return PageUtil.toPage(page.map(alarmHistoryMapper::toDto));
    }

    @Override
    public void save(AlarmHistory alarmHistory) {
        if (alarmHistory == null) {
            throw new IllegalArgumentException("alarmHistory 不能为 null!");
        }
        alarmHistoryRepository.save(alarmHistory);
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private String getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>(4);
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.isEmpty()) {
            return "";
        }
        return argList.size() == 1 ? JSONUtil.toJsonStr(argList.get(0)) : JSONUtil.toJsonStr(argList);
    }

    @Override
    public Object findByErrDetail(Long id) {
        AlarmHistory alarmHistory = alarmHistoryRepository.findById(id).orElseGet(AlarmHistory::new);
        ValidationUtil.isNull(alarmHistory.getId(), "Log", "id", id);
        String details = alarmHistory.getDetail();
        return Dict.create().set("exception", details);
    }

    @Override
    public void download(List<AlarmHistory> alarmHistories, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (AlarmHistory alarmHistory : alarmHistories) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("名称", alarmHistory.getName());
            map.put("等级", alarmHistory.getLevel());
            map.put("创建日期", alarmHistory.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByError() {
        alarmHistoryRepository.deleteByLogType("ERROR");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByInfo() {
        alarmHistoryRepository.deleteByLogType("INFO");
    }
}
