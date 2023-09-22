package me.zhengjie.service.modules.message.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.service.modules.message.service.dto.MessageNotifyDto;
import me.zhengjie.service.modules.message.service.MessageService;
import me.zhengjie.service.modules.message.utils.HttpClientUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * MessageServiceImpl
 * @author wuhao
 * @createTime 2023-09-20
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    public static void main(String[] args) {
        MessageService messageService = new MessageServiceImpl();
        MessageNotifyDto messageNotify = MessageNotifyDto.builder().setType("notify")
                .setContent("test");
        messageService.sendMessage(messageNotify);
    }

    @Override
    public void sendMessage(MessageNotifyDto messageNotify) {
        try {
            sendBySimple(messageNotify.getUrl(), messageNotify.getContent());
        } catch (Exception e){
            log.error("sendMessage error:", e);
        }
    }
    

    private void sendBySimple(String url, String msgContent) {
        Map<String, Object> body = new HashMap<>();
        body.put("msg_type", "text");
        Map<String, Object> content = new HashMap<>();
        content.put("text", msgContent);
        body.put("content", content);
        HttpClientUtils.sendHttpRequest(url, JSON.toJSONString(body));
    }

}
