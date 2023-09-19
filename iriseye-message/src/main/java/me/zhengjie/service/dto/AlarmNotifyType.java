package me.zhengjie.service.dto;

/**
 * @author wuhao
 * @createTime 2022-03-24 14:07:00
 */
public enum AlarmNotifyType {
    MAIL(0, "邮件", "mail"),
    MSG(1, "消息", "msg"),
    PHONE(2, "电话", "phone"),
    SMS(3, "短信", "sms"),;

    int type;
    String msg;
    String sign;

    AlarmNotifyType(int type, String msg, String sign) {
        this.type = type;
        this.msg = msg;
        this.sign = sign;
    }

    public static AlarmNotifyType parse(int level){
        switch (level){
            case 0:
                return MAIL;
            case 1:
                return MSG;
            case 2:
                return PHONE;
            case 3:
                return SMS;
            default:
                return MSG;
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


}
