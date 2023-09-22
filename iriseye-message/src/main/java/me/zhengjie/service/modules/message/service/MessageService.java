package me.zhengjie.service.modules.message.service;

import me.zhengjie.service.modules.message.service.dto.MessageNotifyDto;

/**
 * MessageService
 * 
 * @author wuhao
 * @createTime 2023-09-20
 */
public interface  MessageService {
    /**
     * sendMessage
     * 
     * @param messageNotify
     */
   void sendMessage(MessageNotifyDto messageNotify);
}
