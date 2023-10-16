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
import me.zhengjie.service.watcher.modules.service.DataSearchService;
import me.zhengjie.service.watcher.modules.service.dto.DataSearchDto;
import me.zhengjie.service.watcher.modules.service.dto.DataSearchQueryCriteria;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@RequiredArgsConstructor
@Service(value = "dataSearchService")
public class DataSearchServiceImpl implements DataSearchService {

    @Override
    public PageResult<DataSearchDto> queryAll(DataSearchQueryCriteria criteria, Pageable pageable) {
        ArrayList<DataSearchDto> arrayList = new ArrayList<>();
        arrayList.add(new DataSearchDto("1", "2"));
        return PageUtil.toPage(arrayList, arrayList.size());
    }

    @Override
    public List<DataSearchDto> queryAll(DataSearchQueryCriteria criteria) {
        ArrayList<DataSearchDto> arrayList = new ArrayList<>();
        arrayList.add(new DataSearchDto("1", "2"));
        return arrayList;
    }
}
