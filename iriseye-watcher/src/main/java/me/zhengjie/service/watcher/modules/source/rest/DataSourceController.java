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
package me.zhengjie.service.watcher.modules.source.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.service.watcher.modules.source.domain.DataSource;
import me.zhengjie.service.watcher.modules.source.service.DataSourceService;
import me.zhengjie.service.watcher.modules.source.service.dto.DataSourceDto;
import me.zhengjie.service.watcher.modules.source.service.dto.DataSourceQueryCriteria;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
* @author zhanghouying
* @date 2019-08-24
*/
@Api(tags = "监控：数据源管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/datasource")
public class DataSourceController {
	
    private final DataSourceService dataSourceService;

	@ApiOperation("导出数据库数据")
	@GetMapping(value = "/download")
	@PreAuthorize("@el.check('datasource:list')")
	public void exportDatabase(HttpServletResponse response, DataSourceQueryCriteria criteria) throws IOException {
		dataSourceService.download(dataSourceService.queryAll(criteria), response);
	}

    @ApiOperation(value = "查询数据库")
    @GetMapping
	@PreAuthorize("@el.check('datasource:list')")
    public ResponseEntity<PageResult<DataSourceDto>> queryDatabase(DataSourceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(dataSourceService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @ApiOperation("查询数据源名称")
    @GetMapping(value = "/names")
    @PreAuthorize("@el.check('tasks:list')")
    public ResponseEntity<PageResult<DataSourceDto>> queryQuartzRTask(DataSourceQueryCriteria criteria){
        return new ResponseEntity<>(dataSourceService.queryAllNames(criteria), HttpStatus.OK);
    }


    @Log("新增数据库")
    @ApiOperation(value = "新增数据库")
    @PostMapping
	@PreAuthorize("@el.check('datasource:add')")
    public ResponseEntity<Object> createDatabase(@Validated @RequestBody DataSource resources){
		dataSourceService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改数据库")
    @ApiOperation(value = "修改数据库")
    @PutMapping
	@PreAuthorize("@el.check('datasource:edit')")
    public ResponseEntity<Object> updateDatabase(@Validated @RequestBody DataSource resources){
        dataSourceService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除数据库")
    @ApiOperation(value = "删除数据库")
    @DeleteMapping
	@PreAuthorize("@el.check('datasource:del')")
    public ResponseEntity<Object> deleteDatabase(@RequestBody Set<String> ids){
        dataSourceService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@Log("测试数据库链接")
	@ApiOperation(value = "测试数据库链接")
	@PostMapping("/testConnect")
	@PreAuthorize("@el.check('datasource:testConnect')")
	public ResponseEntity<Object> testConnect(@Validated @RequestBody DataSource resources){
		return new ResponseEntity<>(dataSourceService.testConnection(resources),HttpStatus.CREATED);
	}

}
