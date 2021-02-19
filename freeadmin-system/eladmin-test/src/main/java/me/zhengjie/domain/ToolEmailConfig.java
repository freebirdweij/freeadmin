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
package me.zhengjie.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author weij
* @date 2021-01-25
**/
@Entity
@Data
@Table(name="tool_email_config")
public class ToolEmailConfig implements Serializable {

    @Id
    @Column(name = "config_id")
    @ApiModelProperty(value = "ID")
    private Long configId;

    @Column(name = "from_user")
    @ApiModelProperty(value = "收件人")
    private String fromUser;

    @Column(name = "host")
    @ApiModelProperty(value = "邮件服务器SMTP地址")
    private String host;

    @Column(name = "pass")
    @ApiModelProperty(value = "密码")
    private String pass;

    @Column(name = "port")
    @ApiModelProperty(value = "端口")
    private String port;

    @Column(name = "user")
    @ApiModelProperty(value = "发件者用户名")
    private String user;

    public void copy(ToolEmailConfig source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}