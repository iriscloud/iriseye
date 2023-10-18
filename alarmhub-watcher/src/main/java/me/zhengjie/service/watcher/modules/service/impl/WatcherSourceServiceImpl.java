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
package me.zhengjie.service.watcher.modules.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.service.watcher.modules.domain.WatcherSource;
import me.zhengjie.service.watcher.modules.repository.WatcherSourceRepository;
import me.zhengjie.service.watcher.modules.service.WatcherSourceService;
import me.zhengjie.service.watcher.modules.service.dto.WatcherSourceDto;
import me.zhengjie.service.watcher.modules.service.dto.WatcherSourceQueryCriteria;
import me.zhengjie.service.watcher.modules.service.mapstruct.WatcherSourceMapper;
import me.zhengjie.task.sql.SqlExecutor;
import me.zhengjie.utils.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanghouying
 * @date 2019-08-24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WatcherSourceServiceImpl implements WatcherSourceService {

    private final WatcherSourceRepository dataSourceRepository;
    private final WatcherSourceMapper dataSourceMapper;

    private static Cache<String, WatcherSource> CACHE = Caffeine.newBuilder().maximumSize(1000)
            .expireAfterWrite(2, TimeUnit.MINUTES).build();

    @Override
    public PageResult<WatcherSourceDto> queryAll(WatcherSourceQueryCriteria criteria, Pageable pageable) {
        Page<WatcherSource> page = dataSourceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(dataSourceMapper::toDto));
    }

    @Override
    public List<WatcherSourceDto> queryAll(WatcherSourceQueryCriteria criteria) {
        return dataSourceMapper.toDto(dataSourceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public PageResult<WatcherSourceDto> queryAllNames(WatcherSourceQueryCriteria criteria) {
        List<WatcherSourceDto> taskDtos = queryAll(criteria);
        WatcherSourceDto dataSourceDto = new WatcherSourceDto();
        dataSourceDto.setName("none");
        dataSourceDto.setId("");
        taskDtos.add(dataSourceDto);
        PageResult<WatcherSourceDto> pageResult = PageUtil.toPage(taskDtos, taskDtos.size());
        return pageResult;
    }


    @Override
    public WatcherSourceDto findById(String id) {
        WatcherSource database = dataSourceRepository.findById(id).orElseGet(WatcherSource::new);
        ValidationUtil.isNull(database.getId(), "Database", "id", id);
        return dataSourceMapper.toDto(database);
    }

    @Override
    public WatcherSource findByName(String name) {
        return CACHE.get(name, s -> {
            WatcherSource database = dataSourceRepository.findByName(name);
            if (database == null) {
                database = new WatcherSource();
            }
            return database;
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(WatcherSource resources) {
        resources.setId(IdUtil.simpleUUID());
        CACHE.put(resources.getName(), resources);
        dataSourceRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(WatcherSource resources) {
        WatcherSource database = dataSourceRepository.findById(resources.getId()).orElseGet(WatcherSource::new);
        ValidationUtil.isNull(database.getId(), "Database", "id", resources.getId());
        database.copy(resources);
        CACHE.put(resources.getName(), resources);
        dataSourceRepository.save(database);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<String> ids) {
        for (String id : ids) {
            dataSourceRepository.deleteById(id);
        }
    }

    @Override
    public boolean testConnection(WatcherSource resources) {
        try {
            return SqlExecutor.testConnection(resources.getUrl(), resources.getUserName(), resources.getPwd());
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public void download(List<WatcherSourceDto> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (WatcherSourceDto databaseDto : queryAll) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("数据库名称", databaseDto.getName());
            map.put("数据库连接地址", databaseDto.getUrl());
            map.put("用户名", databaseDto.getUserName());
            map.put("创建日期", databaseDto.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

}
