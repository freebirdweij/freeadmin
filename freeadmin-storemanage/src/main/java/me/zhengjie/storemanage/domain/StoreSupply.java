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
package me.zhengjie.storemanage.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.User;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author freebirdweij
* @date 2021-02-27
**/
@Entity
@Getter
@Setter
@Table(name="store_supply")
public class StoreSupply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supply_id")
    @ApiModelProperty(value = "ID")
    private Long supplyId;

    @Column(name = "supply_code",unique = true,nullable = false)
    @NotNull
    @ApiModelProperty(value = "供应商号")
    private String supplyCode;

    @Column(name = "name",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "供应商名称")
    private String name;

    @Column(name = "contact",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "联系人")
    private String contact;

    @Column(name = "phone",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "联系电话")
    private String phone;

    @Column(name = "fax")
    @ApiModelProperty(value = "传真")
    private String fax;

    @Column(name = "address",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "地址")
    private String address;

    @Column(name = "zipcode")
    @ApiModelProperty(value = "邮政编码")
    private String zipcode;

    @Column(name = "webadd")
    @ApiModelProperty(value = "网址")
    private String webadd;

    @Column(name = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

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

    public void copy(StoreSupply source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}