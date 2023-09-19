package me.zhengjie.service.dto;

/**
 * @author wuhao
 * @createTime 2022-03-24 14:07:00
 */
public enum AlarmLevel {
    RECOVER(0, "业务恢复", "RECOVER"),
    MAJOR_FAILURE(1, "重大故障", "MAJOR_FAILURE"),
    FAILURE(2, "业务故障", "FAILURE"),
    WARNING(3, "服务预警", "WARNING"),
    NOTIFY(4, "业务通知", "NOTIFY");

    int type;
    String msg;
    String sign;

    AlarmLevel(int type, String msg, String sign) {
        this.type = type;
        this.msg = msg;
        this.sign = sign;
    }

    public static AlarmLevel parse(int level){
        switch (level){
            case 0:
                return RECOVER;
            case 1:
                return MAJOR_FAILURE;
            case 2:
                return FAILURE;
            case 3:
                return WARNING;
            case 4:
            default:
                return NOTIFY;
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
