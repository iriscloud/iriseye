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
package me.zhengjie.task.sql;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.StringUtils;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.service.watcher.modules.domain.WatcherSource;
import me.zhengjie.service.watcher.modules.util.WatcherSourceEnum;
import me.zhengjie.utils.CloseUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author /
 */
@Slf4j
public class SqlExecutor {

    private static Cache<String, DataSource> SOURCE = Caffeine.newBuilder().maximumSize(1000)
            .expireAfterWrite(5, TimeUnit.MINUTES).
            removalListener((key, value, cause) -> {
                log.info("expire for source:{}", key);
            }).build();

    /**
     * 获取数据源
     *
     * @param sourceUrl /
     * @param userName  /
     * @param password  /
     * @return DataSource
     */
    public static DataSource getDataSource(String sourceUrl, String userName, String password) {
        return SOURCE.get(sourceUrl, s -> createDataSource(sourceUrl, userName, password));
    }

    public static List<Map<String, Object>> executeSql(WatcherSource source, String sql) {
        Connection connection = null;
        Statement st = null;
        ResultSet resultSet = null;
        List<Map<String, Object>> rests = new ArrayList<>();
        try {
            connection = getConnection(source.getUrl(), source.getUserName(), source.getPwd());
            st = connection.createStatement();
            resultSet = st.executeQuery(sql);
            int count = resultSet.getMetaData().getColumnCount();
            List<String> cNames = new ArrayList<>();

            for (int i = 1; i <= count; i++) {
                cNames.add(resultSet.getMetaData().getColumnName(i));
            }
            while (resultSet.next()) {
                Map<String, Object> maps = new HashMap<>();
                for (String name : cNames) {
                    maps.put(name, resultSet.getObject(name));
                }
                rests.add(maps);
            }
            return rests;
        } catch (Exception e) {
            log.error("executeSql fail:{}", sql, e);
        } finally {
            releaseConnection(connection);
            CloseUtil.close(st);
        }
        return rests;
    }

    public static DataSource createDataSource(String sourceUrl, String userName, String password) {
        DruidDataSource druidDataSource = new DruidDataSource();
        String className;
        try {
            className = DriverManager.getDriver(sourceUrl.trim()).getClass().getName();
        } catch (SQLException e) {
            throw new RuntimeException("Get class name error: =" + sourceUrl);
        }
        if (StringUtils.isEmpty(className)) {
            WatcherSourceEnum dataTypeEnum = WatcherSourceEnum.urlOf(sourceUrl);
            if (null == dataTypeEnum) {
                throw new RuntimeException("Not supported data type: sourceUrl=" + sourceUrl);
            }
            druidDataSource.setDriverClassName(dataTypeEnum.getDriver());
        } else {
            druidDataSource.setDriverClassName(className);
        }


        druidDataSource.setUrl(sourceUrl);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        // 配置获取连接等待超时的时间
        druidDataSource.setMaxWait(3000);
        // 配置初始化大小、最小、最大
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxActive(1);

        // 如果链接出现异常则直接判定为失败而不是一直重试
        druidDataSource.setBreakAfterAcquireFailure(true);
        try {
            druidDataSource.init();
        } catch (SQLException e) {
            log.error("Exception during pool initialization", e);
            throw new RuntimeException(e.getMessage());
        }

        return druidDataSource;
    }

    public static Connection getConnection(String sourceUrl, String userName, String password) {
        DataSource dataSource = getDataSource(sourceUrl, userName, password);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (Exception ignored) {
        }
        try {
            int timeOut = 5;
            if (null == connection || connection.isClosed() || !connection.isValid(timeOut)) {
                log.info("connection is closed or invalid, retry get connection!");
                connection = dataSource.getConnection();
            }
        } catch (Exception e) {
            log.error("create connection error, sourceUrl: {}", sourceUrl);
            throw new RuntimeException("create connection error, sourceUrl: " + sourceUrl);
        }
        return connection;
    }

    private static void releaseConnection(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                log.error("connection close error：" + e.getMessage());
            }
        }
    }

    public static boolean testConnection(String sourceUrl, String userName, String password) {
        Connection connection = null;
        try {
            connection = getConnection(sourceUrl, userName, password);
            if (null != connection) {
                return true;
            }
        } catch (Exception e) {
            log.info("Get connection failed:" + e.getMessage());
        } finally {
            releaseConnection(connection);
        }
        return false;
    }


    /**
     * 批量执行sql
     *
     * @param connection /
     * @param sqlList    /
     */
    public static void batchExecute(Connection connection, List<String> sqlList) {
        Statement st = null;
        try {
            st = connection.createStatement();
            for (String sql : sqlList) {
                if (sql.endsWith(";")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                st.addBatch(sql);
            }
            st.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            CloseUtil.close(st);
        }
    }

}
