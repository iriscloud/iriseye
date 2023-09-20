package me.zhengjie.service.watcher.modules.message;

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
   void sendMessage(MessageNotify messageNotify);
}
