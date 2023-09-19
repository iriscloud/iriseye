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

import lombok.RequiredArgsConstructor;
import me.zhengjie.exception.EntityExistException;
import me.zhengjie.service.watcher.modules.manager.domain.AlarmShield;
import me.zhengjie.service.watcher.modules.manager.repository.AlarmShieldRepository;
import me.zhengjie.service.watcher.modules.manager.service.AlarmShieldService;
import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmShieldDto;
import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmShieldQueryCriteria;
import me.zhengjie.service.watcher.modules.manager.service.mapstruct.AlarmShieldMapper;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zheng Jie
 * @date 2019-09-05
 */
@Service
@RequiredArgsConstructor
public class AlarmShieldServiceImpl implements AlarmShieldService {

    private final AlarmShieldRepository alarmShieldRepository;
    private final AlarmShieldMapper alarmShieldMapper;


    @Override
    public Map<String, Object> queryAll(AlarmShieldQueryCriteria criteria, Pageable pageable) {
        Page<AlarmShield> page = alarmShieldRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
//        return PageUtil.toPage(page.map(alarmShieldMapper::toDto).getContent(), page.getTotalElements());
        return new ConcurrentHashMap<>();
    }

    @Override
    public List<AlarmShieldDto> queryAll(AlarmShieldQueryCriteria criteria) {
        List<AlarmShield> list = alarmShieldRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder));
        return alarmShieldMapper.toDto(list);
    }

    @Override
    public List<AlarmShieldDto> queryAll() {
        return queryAll(new AlarmShieldQueryCriteria());
    }

    @Override
    @Cacheable(key = "'id:' + #p0")
    public AlarmShieldDto findById(Long id) {
        AlarmShield job = alarmShieldRepository.findById(id).orElseGet(AlarmShield::new);
        ValidationUtil.isNull(job.getId(), "Job", "id", id);
        return alarmShieldMapper.toDto(job);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(AlarmShield resources) {
        AlarmShield job = alarmShieldRepository.findByName(resources.getName());
        if (job != null) {
            throw new EntityExistException(AlarmShield.class, "name", resources.getName());
        }
        alarmShieldRepository.save(resources);
    }

    @Override
    @CacheEvict(key = "'id:' + #p0.id")
    @Transactional(rollbackFor = Exception.class)
    public void update(AlarmShield resources) {
        AlarmShield AlarmShield = alarmShieldRepository.findById(resources.getId()).orElseGet(AlarmShield::new);
        AlarmShield old = alarmShieldRepository.findByName(resources.getName());
        if (old != null && !old.getId().equals(resources.getId())) {
            throw new EntityExistException(AlarmShield.class, "name", resources.getName());
        }
        ValidationUtil.isNull(resources.getId(), "Job", "id", resources.getId());
        resources.setId(resources.getId());
        alarmShieldRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        alarmShieldRepository.deleteAllByIdIn(ids);
    }

    @Override
    public void download(List<AlarmShieldDto> jobDtos, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (AlarmShieldDto jobDTO : jobDtos) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("名称", jobDTO.getName());
            map.put("规则", jobDTO.getRuleContent());
            map.put("创建日期", jobDTO.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

}
