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
package me.zhengjie.test.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.Job;
import me.zhengjie.modules.system.domain.Role;
import me.zhengjie.modules.system.domain.User;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author weij
* @date 2021-01-26
**/
@Entity
@Getter
@Setter
@Table(name="app_property")
public class AppProperty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    @ApiModelProperty(value = "ID")
    private Long propertyId;

    @Column(name = "name",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "资产名称")
    private String name;

    @Column(name = "enabled",nullable = false)
    @NotNull
    @ApiModelProperty(value = "资产状态，0-闲置，1-在用，2-报废")
    private Integer enabled;

    @Column(name = "property_type",nullable = false)
    @NotNull
    @ApiModelProperty(value = "资产类别，0-设备，1-仪器，2-工具，3-家具")
    private Integer propertyType;

    @Column(name = "dept_id")
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @OneToOne
    @JoinColumn(name = "dept_id",insertable = false,updatable = false)
    @ApiModelProperty(value = "存放部门")
    private Dept dept;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = true)
    @ApiModelProperty(value = "责任人")
    private User user;

    @Column(name = "create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @Column(name = "update_by")
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private Timestamp createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    public void copy(AppProperty source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}