/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.service.watcher.modules.manager.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zheng Jie
 * @date 2019-09-05
 */
@Getter
@Setter
@Entity
@Table(name = "elalert_alarm_shield")
@NoArgsConstructor
public class AlarmShield extends BaseEntity implements Serializable {

    @Id
    @Column(name = "alarm_shield_id")
    @ApiModelProperty(value = "ID", hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "告警名")
    private String name;

    @ApiModelProperty(value = "告警级别")
    private String level;

    @ApiModelProperty(value = "规则类型")
    private String ruleType;

    @ApiModelProperty(value = "规则内容")
    private String ruleContent;

    @ApiModelProperty(value = "参考规则内容")
    private String ruleContentRefer;

    @ApiModelProperty(value = "对比方式")
    private String compare;

    @ApiModelProperty(value = "预期值")
    private String expected;

    @ApiModelProperty(value = "执行频率")
    private String execTime;

    @ApiModelProperty(value = "执行次数")
    private int execCount;

    @ApiModelProperty(value = "预案连接")
    private String dealUrl;

    @ApiModelProperty(value = "通知类型")
    private String notifyType;

    @ApiModelProperty(value = "回调地址")
    private String callBackUrl;


    public void copy(AlarmShield source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}