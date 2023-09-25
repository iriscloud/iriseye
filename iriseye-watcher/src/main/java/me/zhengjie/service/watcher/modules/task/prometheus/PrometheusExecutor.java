package me.zhengjie.service.watcher.modules.task.prometheus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.service.modules.message.utils.HttpClientUtils;
import me.zhengjie.service.watcher.modules.domain.WatcherSource;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhao
 * @createTime 2023-09-22
 */
@Slf4j
public class PrometheusExecutor {
    public static final Logger LOGGER = LoggerFactory.getLogger(PrometheusExecutor.class);

    public static void executePql(WatcherSource source, String pql) {

        try {
            String url = source.getUrl();
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            StringBuilder sb = new StringBuilder();
            sb.append("query=").append(pql);
            long beginTime = System.currentTimeMillis();
            HttpClientUtils.sendHttpRequest(url, headers, sb.toString(), new AsyncCompletionHandler() {
                @Override
                public Object onCompleted(Response response) throws Exception {
                    long handleTime = System.currentTimeMillis() - beginTime;
                    LOGGER.info("send  url:{} time:{} ", url, handleTime);
                    return getContent(response.getResponseBody());
                }

                @Override
                public void onThrowable(Throwable t) {
                    long handleTime = System.currentTimeMillis() - beginTime;
                    LOGGER.error("send error url:{} time:{} error:{}", url, handleTime, t.getMessage());
                }
            });
        } catch (Exception e) {
            log.error("executeSql fail:{}", pql, e);
        }
    }


    private static JSONObject getContent(String content) {
        JSONObject jsonObject = JSON.parseObject(content);
        System.out.println(jsonObject.toJSONString());
        return jsonObject;
    }

}
