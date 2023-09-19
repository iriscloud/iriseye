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
import lombok.NoArgsConstructor;
import me.zhengjie.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zheng Jie
 * @date 2019-09-05
 */
@Entity
@Table(name = "elalert_alarm_rule")
@NoArgsConstructor
public class AlarmRule extends BaseEntity implements Serializable {

    @Id
    @Column(name = "alarm_rule_id")
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


    public void copy(AlarmRule source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleContent() {
        return ruleContent;
    }

    public void setRuleContent(String ruleContent) {
        this.ruleContent = ruleContent;
    }

    public String getRuleContentRefer() {
        return ruleContentRefer;
    }

    public void setRuleContentRefer(String ruleContentRefer) {
        this.ruleContentRefer = ruleContentRefer;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public int getExecCount() {
        return execCount;
    }

    public void setExecCount(int execCount) {
        this.execCount = execCount;
    }

    public String getDealUrl() {
        return dealUrl;
    }

    public void setDealUrl(String dealUrl) {
        this.dealUrl = dealUrl;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }
}