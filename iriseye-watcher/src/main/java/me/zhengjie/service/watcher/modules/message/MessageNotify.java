package me.zhengjie.service.watcher.modules.message;

/**
 * @author wuhao
 * @createTime 2023-09-20
 */
public class MessageNotify {
    
    
    private String type;
    
    private String url;
    
    private String content;

    public String getType() {
        return type;
    }

    public MessageNotify setType(String type) {
        this.type = type;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MessageNotify setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageNotify setContent(String content) {
        this.content = content;
        return this;
    }
    
    public static MessageNotify builder(){
        return new MessageNotify();
    }
}
