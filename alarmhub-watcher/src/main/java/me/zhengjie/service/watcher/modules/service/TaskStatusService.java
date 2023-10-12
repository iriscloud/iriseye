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
package me.zhengjie.service.watcher.modules.service;

import me.zhengjie.service.watcher.modules.domain.TaskStatus;
import me.zhengjie.service.watcher.modules.service.dto.TaskStatusQueryCriteria;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
public interface TaskStatusService {

    /**
     * 分页查询
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    PageResult<TaskStatus> queryAll(TaskStatusQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部
     *
     * @param criteria 条件
     * @return /
     */
    List<TaskStatus> queryAll(TaskStatusQueryCriteria criteria);


    /**
     * 创建
     *
     * @param resources /
     */
    void create(TaskStatus resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(TaskStatus resources);

    /**
     * 删除任务
     *
     * @param ids /
     */
    void delete(Set<Long> ids);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return /
     */
    TaskStatus findById(Long id);

}
