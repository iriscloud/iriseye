package me.zhengjie.service.watcher.modules.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Entity
@Getter
@Setter
@Table(name="watcher_datasource")
public class WatcherSource extends BaseEntity implements Serializable {

    @Id
    @Column(name = "wd_id", length = 64)
    @ApiModelProperty(value = "ID", hidden = true)
    private String id;

    @ApiModelProperty(value = "类型")
    @Column(length = 64)
    private String type;
    
    @ApiModelProperty(value = "数据源名称")
    @Column(length = 64)
    private String name;

    @ApiModelProperty(value = "数据源连接地址")
    private String url;


    @ApiModelProperty(value = "用户名")
    @Column(length = 64)
    private String userName;

    @ApiModelProperty(value = "密码")
    @Column(length = 64)
    private String pwd;


    public void copy(WatcherSource source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}