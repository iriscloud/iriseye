package me.zhengjie.service.watcher.modules.source.task.prometheus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用与探测
 *
 * @author wuhao
 * @description: DetectorApi
 * @createTime 2022/05/14 22:54:00
 */

@Service
public class DetectorApiPrometheus implements DetectorApi {

    @Override
    public DetectorResponse detectorApi(DetectorRequest detector) {
        String content = getContent(detector);
        DetectorResponse response = getResponse(detector, content);
        return response;
    }

    /**
     * 获取返回内容
     *
     * @return
     */
    public String getContent(DetectorRequest request) {
        String url = request.getDataSource().getUrl();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        StringBuilder sb = new StringBuilder();
        sb.append("query=").append(request.getQuartzTask().getParams()).append("&");
        sb.append("time=").append(System.currentTimeMillis());
//        HttpResponse response = HttpClientUtil.httpPost(url, headers, sb.toString());
//        String content = HttpClientUtil.getContent(response);
//        return content;
        return null;
    }

    /**
     * 获取返回内容
     *
     * @return
     */
    public DetectorResponse getResponse(DetectorRequest request, String content) {
        String value = getResult(content);
        Long resultValue = Long.valueOf(value.split("\\.")[0]);
//        Long expectedValue = Long.valueOf(request.getExpected());
//        boolean result = resultValue > expectedValue;
        DetectorResponse response = new DetectorResponse();
        response.setId(request.getId());
//        response.setType(request.getType());
        response.setRequest(request);
        response.setData(value);
        response.setResult(true);
        return response;
    }

    public String getResult(String content) {
        JSONObject jsonObject = JSON.parseObject(content);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("result");
        JSONObject ret = null;
        if (jsonArray != null && jsonArray.size() > 0) {
            ret = jsonArray.getJSONObject(0);
        }
        if (ret != null) {
            //ret.getJSONArray("value").getString(0) is time
            return ret.getJSONArray("value").getString(1);
        }
        return "0";
    }

}
