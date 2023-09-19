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
import me.zhengjie.service.watcher.modules.manager.domain.AlarmRule;
import me.zhengjie.service.watcher.modules.manager.repository.AlarmRuleRepository;
import me.zhengjie.service.watcher.modules.manager.service.AlarmRuleService;
import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmRuleDto;
import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmRuleQueryCriteria;
import me.zhengjie.service.watcher.modules.manager.service.mapstruct.AlarmRuleMapper;
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
public class AlarmRuleServiceImpl implements AlarmRuleService {

    private final AlarmRuleRepository alarmRuleRepository;
    private final AlarmRuleMapper alarmRuleMapper;


    @Override
    public Map<String, Object> queryAll(AlarmRuleQueryCriteria criteria, Pageable pageable) {
        Page<AlarmRule> page = alarmRuleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return new ConcurrentHashMap<>();
        //        return PageUtil.toPage(page.map(alarmRuleMapper::toDto).getContent(), page.getTotalElements());
    }

    @Override
    public List<AlarmRuleDto> queryAll(AlarmRuleQueryCriteria criteria) {
        List<AlarmRule> list = alarmRuleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder));
        return alarmRuleMapper.toDto(list);
    }

    @Override
    public List<AlarmRuleDto> queryAll() {
        return queryAll(new AlarmRuleQueryCriteria());
    }

    @Override
    @Cacheable(key = "'id:' + #p0")
    public AlarmRuleDto findById(Long id) {
        AlarmRule job = alarmRuleRepository.findById(id).orElseGet(AlarmRule::new);
        ValidationUtil.isNull(job.getId(), "Job", "id", id);
        return alarmRuleMapper.toDto(job);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(AlarmRule resources) {
        AlarmRule job = alarmRuleRepository.findByName(resources.getName());
        if (job != null) {
            throw new EntityExistException(AlarmRule.class, "name", resources.getName());
        }
        alarmRuleRepository.save(resources);
    }

    @Override
    @CacheEvict(key = "'id:' + #p0.id")
    @Transactional(rollbackFor = Exception.class)
    public void update(AlarmRule resources) {
        AlarmRule alarmRule = alarmRuleRepository.findById(resources.getId()).orElseGet(AlarmRule::new);
        AlarmRule old = alarmRuleRepository.findByName(resources.getName());
        if (old != null && !old.getId().equals(resources.getId())) {
            throw new EntityExistException(AlarmRule.class, "name", resources.getName());
        }
        ValidationUtil.isNull(resources.getId(), "Job", "id", resources.getId());
        resources.setId(resources.getId());
        alarmRuleRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        alarmRuleRepository.deleteAllByIdIn(ids);
    }

    @Override
    public void download(List<AlarmRuleDto> jobDtos, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (AlarmRuleDto jobDTO : jobDtos) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("名称", jobDTO.getName());
            map.put("规则", jobDTO.getRuleContent());
            map.put("创建日期", jobDTO.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

}
