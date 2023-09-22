package me.zhengjie.service.watcher.modules.task.prometheus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.service.modules.message.utils.HttpClientUtils;
import me.zhengjie.service.watcher.modules.domain.WatcherSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhao
 * @createTime 2023-09-22
 */
@Slf4j
public class PrometheusExecutor {
    public static String executeSql(WatcherSource source, String pql) {
        String pqlRet = "";
        try {
            String url = source.getUrl();
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            StringBuilder sb = new StringBuilder();
            sb.append("query=").append(pql).append("&");
            sb.append("time=").append(System.currentTimeMillis());
//            HttpClientUtils.sendHttpRequest(url, headers, sb.toString());
//            String content = HttpClientUtil.getContent(response);
//            JSONObject jsonObject = JSON.parseObject(content);
//            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("result");
//            JSONObject ret = null;
//            if (jsonArray != null && jsonArray.size() > 0) {
//                ret = jsonArray.getJSONObject(0);
//            }
//            if (ret != null) {
//                //ret.getJSONArray("value").getString(0) is time
//                return ret.getJSONArray("value").getString(1);
//            }
        } catch (Exception e) {
            log.error("executeSql fail:{}", pql, e);
        } 
        return pqlRet;
    }

}
