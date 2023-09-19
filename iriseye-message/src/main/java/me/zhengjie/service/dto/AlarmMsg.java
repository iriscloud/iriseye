package me.zhengjie.service.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;

/**
 * 告警通知接口
 *
 * @author wuhao
 * @createTime 2022-03-17 21:48:00
 */
@Data
public class AlarmMsg {

    /**
     * 告警ID
     */
    private long id;
    /**
     * 告警标识【类别】
     */
    private String sign;
    /**
     * 告警级别
     */
    private AlarmLevel alarmLevel;
    /**
     * 通知类型
     */
    private AlarmNotifyType alarmNotifyType;
    /**
     * 时间
     */
    private String time;
    
    /**
     * 来源
     */
    private String source;
    
    /**
     * 分组
     */
    private String group;
    

    /**
     * 操作
     */
    private String item;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String desc;
    
    /**
     * 详情连接
     */
    private String detailUrl;
    /**
     * 处理建议
     */
    private String dealUrl;
    /**
     * 值班人员
     */
    private String dutyUser;
    /**
     * 处理人
     */
    private String dealUser;
    /**
     * 处理详情
     */
    private String opt;




    /**
     * toNotifyMsg
     *
     * @return
     */
    public String toNotifyMsg() {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(this.getSource())) {
            sb.append("【告警来源】:").append(this.getSource()).append("\n");
        }
        if (StringUtils.isNotEmpty(this.getTime())) {
            sb.append("【触发时间】:").append(this.getTime()).append("\n");
        }
        if (StringUtils.isNotEmpty(this.getDesc())) {
            sb.append("【告警描述】:").append("\n\t");
            sb.append(this.getDesc());
        }
        if (StringUtils.isNotEmpty(this.getDealUrl())) {
            sb.append("【处理建议】:").append("\n\t").append(this.getDealUrl()).append("\n");
        }
        if (StringUtils.isNotBlank(this.getDutyUser())) {
            sb.append("【值班人】:").append(this.getDutyUser()).append("\n");
        }

        return sb.toString();
    }


    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    

}
