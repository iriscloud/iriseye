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
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.service.watcher.modules.service.WatcherSourceTypeService;
import me.zhengjie.service.watcher.modules.service.dto.WatcherSourceTypeDto;
import me.zhengjie.service.watcher.modules.util.DataSourceType;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghouying
 * @date 2019-08-24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WatcherSourceTypeServiceImpl implements WatcherSourceTypeService {


    @Override
    public PageResult<WatcherSourceTypeDto> queryAllTypeNames() {
        List<WatcherSourceTypeDto> taskDtos = new ArrayList<>();
        for (DataSourceType watcherSourceType : DataSourceType.values()) {
            WatcherSourceTypeDto watcherSourceTypeDto = new WatcherSourceTypeDto();
            watcherSourceTypeDto.setName(watcherSourceType.name);
            watcherSourceTypeDto.setSubName(watcherSourceType.subName);
            watcherSourceTypeDto.setUrlDesc(watcherSourceType.urlDesc);
            taskDtos.add(watcherSourceTypeDto);
        }
        PageResult<WatcherSourceTypeDto> pageResult = PageUtil.toPage(taskDtos, taskDtos.size());
        return pageResult;
    }


}
