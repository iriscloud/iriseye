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
package me.zhengjie.service.watcher.modules.source.service.impl;

import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.service.watcher.modules.source.domain.DataSource;
import me.zhengjie.service.watcher.modules.source.repository.DataSourceRepository;
import me.zhengjie.service.watcher.modules.source.service.DataSourceService;
import me.zhengjie.service.watcher.modules.source.service.dto.DataSourceDto;
import me.zhengjie.service.watcher.modules.source.service.dto.DataSourceQueryCriteria;
import me.zhengjie.service.watcher.modules.source.service.dto.RTaskDto;
import me.zhengjie.service.watcher.modules.source.service.mapstruct.DataSourceMapper;
import me.zhengjie.service.watcher.modules.source.util.SqlUtils;
import me.zhengjie.utils.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
* @author zhanghouying
* @date 2019-08-24
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class DataSourceServiceImpl implements DataSourceService {

    private final DataSourceRepository dataSourceRepository;
    private final DataSourceMapper dataSourceMapper;

    @Override
    public PageResult<DataSourceDto> queryAll(DataSourceQueryCriteria criteria, Pageable pageable){
        Page<DataSource> page = dataSourceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(dataSourceMapper::toDto));
    }

    @Override
    public List<DataSourceDto> queryAll(DataSourceQueryCriteria criteria){
        return dataSourceMapper.toDto(dataSourceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public PageResult<DataSourceDto> queryAllNames(DataSourceQueryCriteria criteria) {
        List<DataSourceDto> taskDtos = queryAll(criteria);
        DataSourceDto dataSourceDto = new DataSourceDto();
        dataSourceDto.setName("none");
        dataSourceDto.setId("");
        taskDtos.add(dataSourceDto);
        PageResult<DataSourceDto> pageResult = PageUtil.toPage(taskDtos, taskDtos.size());
        return pageResult;
    }

    @Override
    public DataSourceDto findById(String id) {
        DataSource database = dataSourceRepository.findById(id).orElseGet(DataSource::new);
        ValidationUtil.isNull(database.getId(),"Database","id",id);
        return dataSourceMapper.toDto(database);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(DataSource resources) {
        resources.setId(IdUtil.simpleUUID());
        dataSourceRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DataSource resources) {
        DataSource database = dataSourceRepository.findById(resources.getId()).orElseGet(DataSource::new);
        ValidationUtil.isNull(database.getId(),"Database","id",resources.getId());
        database.copy(resources);
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
	public boolean testConnection(DataSource resources) {
		try {
			return SqlUtils.testConnection(resources.getUrl(), resources.getUserName(), resources.getPwd());
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

    @Override
    public void download(List<DataSourceDto> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DataSourceDto databaseDto : queryAll) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("数据库名称", databaseDto.getName());
            map.put("数据库连接地址", databaseDto.getUrl());
            map.put("用户名", databaseDto.getUserName());
            map.put("创建日期", databaseDto.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
    
}
