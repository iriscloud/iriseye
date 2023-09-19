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
package me.zhengjie.service.watcher.modules.manager.service;

import me.zhengjie.service.watcher.modules.manager.domain.AlarmShield;
import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmShieldDto;
import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmShieldQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2019-09-05
 */
public interface AlarmShieldService {

    /**
     * 根据ID查询
     *
     * @param id /
     * @return /
     */
    AlarmShieldDto findById(Long id);

    /**
     * 创建
     *
     * @param resources /
     * @return /
     */
    void create(AlarmShield resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(AlarmShield resources);

    /**
     * 删除
     *
     * @param ids /
     */
    void delete(Set<Long> ids);

    /**
     * 分页查询
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Map<String, Object> queryAll(AlarmShieldQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部数据
     *
     * @param criteria /
     * @return /
     */
    List<AlarmShieldDto> queryAll(AlarmShieldQueryCriteria criteria);

    /**
     * 查询全部数据
     *
     * @return /
     */
    List<AlarmShieldDto> queryAll();

    /**
     * 导出数据
     *
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<AlarmShieldDto> queryAll, HttpServletResponse response) throws IOException;

}