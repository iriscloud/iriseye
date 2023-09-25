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

import me.zhengjie.service.watcher.modules.domain.WatcherSource;
import me.zhengjie.service.watcher.modules.service.dto.WatcherSourceDto;
import me.zhengjie.service.watcher.modules.service.dto.WatcherSourceQueryCriteria;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author ZhangHouYing
 * @date 2019-08-24
 */
public interface WatcherSourceService {

    /**
     * 分页查询
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    PageResult<WatcherSourceDto> queryAll(WatcherSourceQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部
     *
     * @param criteria 条件
     * @return /
     */
    List<WatcherSourceDto> queryAll(WatcherSourceQueryCriteria criteria);

    /**
     * 查询全部数据源名称
     *
     * @param criteria 条件
     * @return /
     */
    PageResult<WatcherSourceDto> queryAllNames(WatcherSourceQueryCriteria criteria);
    
    /**
     * 根据ID查询
     *
     * @param id /
     * @return /
     */
    WatcherSourceDto findById(String id);

    /**
     * findByName
     *
     * @param name
     * @return
     */
    WatcherSource findByName(String name);

    /**
     * 创建
     *
     * @param resources /
     */
    void create(WatcherSource resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(WatcherSource resources);

    /**
     * 删除
     *
     * @param ids /
     */
    void delete(Set<String> ids);

    /**
     * 测试连接数据库
     *
     * @param resources /
     * @return /
     */
    boolean testConnection(WatcherSource resources);

    /**
     * 导出数据
     *
     * @param queryAll /
     * @param response /
     * @throws IOException e
     */
    void download(List<WatcherSourceDto> queryAll, HttpServletResponse response) throws IOException;

}
