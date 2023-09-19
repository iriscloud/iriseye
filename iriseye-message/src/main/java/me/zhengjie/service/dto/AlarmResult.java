package me.zhengjie.service.dto;

/**
 * @author wuhao
 * @createTime 2022-04-22 18:26:00
 */
public class AlarmResult {
    private int code;
    private String message;

    public AlarmResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static AlarmResult build(int code, String message){
        return new AlarmResult(code, message);
    }
}
