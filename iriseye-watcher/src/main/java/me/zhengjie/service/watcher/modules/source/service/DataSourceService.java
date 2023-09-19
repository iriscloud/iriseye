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
package me.zhengjie.service.watcher.modules.source.service;

import me.zhengjie.service.watcher.modules.source.domain.DataSource;
import me.zhengjie.service.watcher.modules.source.service.dto.DataSourceDto;
import me.zhengjie.service.watcher.modules.source.service.dto.DataSourceQueryCriteria;
import me.zhengjie.service.watcher.modules.source.service.dto.RTaskDto;
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
public interface DataSourceService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    PageResult<DataSourceDto> queryAll(DataSourceQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部
     * @param criteria 条件
     * @return /
     */
    List<DataSourceDto> queryAll(DataSourceQueryCriteria criteria);

    /**
     * 查询全部数据源名称
     * @param criteria 条件
     * @return /
     */
    PageResult<DataSourceDto> queryAllNames(DataSourceQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    DataSourceDto findById(String id);

    /**
     * 创建
     * @param resources /
     */
    void create(DataSource resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(DataSource resources);

    /**
     * 删除
     * @param ids /
     */
    void delete(Set<String> ids);

	/**
	 * 测试连接数据库
	 * @param resources /
	 * @return /
	 */
	boolean testConnection(DataSource resources);

    /**
     * 导出数据
     * @param queryAll /
     * @param response /
     * @throws IOException e
     */
    void download(List<DataSourceDto> queryAll, HttpServletResponse response) throws IOException;
    
}
