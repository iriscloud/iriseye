package me.zhengjie.service.watcher.modules.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.zhengjie.base.BaseDTO;

import java.io.Serializable;

/**
 * @author wuhao
 * @createTime 2023-09-25
 */
@Getter
@Setter
@NoArgsConstructor
public class WatcherSourceTypeDto extends BaseDTO implements Serializable {


    /**
     * 类型名称
     */
    private String name;


    /**
     * 子名称
     */
    private String subName;

    /**
     * 连接示例
     */
    private String urlDesc;

}
