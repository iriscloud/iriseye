///*
// *  Copyright 2019-2020 Zheng Jie
// *
// *  Licensed under the Apache License, Version 2.0 (the "License");
// *  you may not use this file except in compliance with the License.
// *  You may obtain a copy of the License at
// *
// *  http://www.apache.org/licenses/LICENSE-2.0
// *
// *  Unless required by applicable law or agreed to in writing, software
// *  distributed under the License is distributed on an "AS IS" BASIS,
// *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  See the License for the specific language governing permissions and
// *  limitations under the License.
// */
//package me.zhengjie.service.modules.message.domain;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.sql.Timestamp;
//
///**
// * @author wuhao
// */
//@Entity
//@Data
//@Table(name = "message_notify_filter")
//public class MessageNotifyFilter implements Serializable {
//
//    @Id
//    @Column(name = "log_id")
//    @ApiModelProperty(value = "ID", hidden = true)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ApiModelProperty(value = "标签", hidden = true)
//    private String tag;
//    
//}
