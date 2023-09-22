package me.zhengjie.service.watcher.modules.task.prometheus;

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
public class DetectorApiPrometheus {


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
