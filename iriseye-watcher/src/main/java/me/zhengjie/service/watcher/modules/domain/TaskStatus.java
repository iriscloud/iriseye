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
package me.zhengjie.service.watcher.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Getter
@Setter
@Entity
@Table(name = "watcher_rule_task_status")
public class TaskStatus extends BaseEntity implements Serializable {

    public static final String TASK_KEY = "TASK_KEY";

    @Id
    @Column(name = "status_id")
    @NotNull(groups = {Update.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @ApiModelProperty(value = "用于子任务唯一标识", hidden = true)
    private String uuid;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @NotBlank
    @ApiModelProperty(value = "查询数据信息")
    private String data;

    @ApiModelProperty(value = "创建时间")
    private long create;

    @ApiModelProperty(value = "是否命中规则")
    private boolean status;

    //create + 持续时间
    @ApiModelProperty(value = "告警触发时间")
    private long alarmTime;

    @ApiModelProperty(value = "消息通知时间")
    private long sendTime;


    @ApiModelProperty(value = "剩余发送次数")
    private long sendCount;

    @ApiModelProperty(value = "是否发送恢复通知")
    private boolean sendRecovery;


}