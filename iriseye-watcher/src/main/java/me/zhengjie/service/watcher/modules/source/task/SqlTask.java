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
package me.zhengjie.service.watcher.modules.source.task;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.RTask;
import me.zhengjie.service.watcher.modules.source.domain.WatcherSource;
import me.zhengjie.service.watcher.modules.source.domain.RuleTask;
import me.zhengjie.service.watcher.modules.source.task.sql.SqlExecutor;
import me.zhengjie.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * Sql Task
 * @author Zheng Jie
 * @date 2019-01-08
 */
@Slf4j
@RTask
@Service("SqlTask")
public class SqlTask implements WatcherTask{

    @Override
    public void run(RuleTask ruleTask, WatcherSource dataSource) {
        if (dataSource == null || StringUtils.isBlank(dataSource.getUrl())){
            return;
        }
        List<Map<String, Object>> rests = SqlExecutor.executeSql(dataSource, ruleTask.getParams());
        //resultSet.getMetaData()
        //resultSet.getA
        System.out.println(JSONObject.toJSON(rests).toString());
    }
}