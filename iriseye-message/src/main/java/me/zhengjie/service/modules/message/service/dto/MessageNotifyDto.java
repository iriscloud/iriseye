package me.zhengjie.service.modules.message.service.dto;

/**
 * @author wuhao
 * @createTime 2023-09-20
 */
public class MessageNotifyDto {
    
    
    private String type;
    
    private String url;
    
    private String content;

    public String getType() {
        return type;
    }

    public MessageNotifyDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MessageNotifyDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageNotifyDto setContent(String content) {
        this.content = content;
        return this;
    }
    
    public static MessageNotifyDto builder(){
        return new MessageNotifyDto();
    }
}
