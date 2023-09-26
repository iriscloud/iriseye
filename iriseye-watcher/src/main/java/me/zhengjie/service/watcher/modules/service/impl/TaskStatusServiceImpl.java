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

import lombok.RequiredArgsConstructor;
import me.zhengjie.service.watcher.modules.domain.TaskStatus;
import me.zhengjie.service.watcher.modules.repository.TaskStatusRepository;
import me.zhengjie.service.watcher.modules.service.TaskStatusService;
import me.zhengjie.service.watcher.modules.service.dto.TaskStatusQueryCriteria;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@RequiredArgsConstructor
@Service(value = "taskStatusService")
public class TaskStatusServiceImpl implements TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;

    @Override
    public PageResult<TaskStatus> queryAll(TaskStatusQueryCriteria criteria, Pageable pageable) {
        return PageUtil.toPage(taskStatusRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable));
    }


    @Override
    public List<TaskStatus> queryAll(TaskStatusQueryCriteria criteria) {
        return taskStatusRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder));
    }

    @Override
    public void create(TaskStatus resources) {
        taskStatusRepository.save(resources);
    }

    @Override
    public void update(TaskStatus resources) {
        taskStatusRepository.save(resources);
    }

    @Override
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            TaskStatus taskStatus = findById(id);
            taskStatusRepository.delete(taskStatus);
        }
    }

    @Override
    public TaskStatus findById(Long id) {
        TaskStatus taskStatus = taskStatusRepository.findById(id).orElseGet(TaskStatus::new);
        ValidationUtil.isNull(taskStatus.getId(), "TaskStatus", "id", id);
        return taskStatus;
    }
}
