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
import me.zhengjie.base.BaseEntity;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.User;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
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
@Table(name="store_operate")
public class StoreOperate extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operate_id")
    @ApiModelProperty(value = "ID")
    private Long operateId;

    @Column(name = "remain_id",nullable = false)
    @NotNull
    @ApiModelProperty(value = "库存ID")
    private Long remainId;

    @Column(name = "user_id",nullable = false)
    @NotNull
    @ApiModelProperty(value = "与本次操作相关人员")
    private Long userId;

    @Column(name = "operate_type",nullable = false)
    @NotNull
    @ApiModelProperty(value = "操作类型，由字典实现")
    private Integer operateType;

    @Column(name = "counts",nullable = false)
    @NotNull
    @ApiModelProperty(value = "操作数量")
    private Long counts;

    @Column(name = "amount",nullable = false)
    @NotNull
    @ApiModelProperty(value = "操作金额")
    private BigDecimal amount;

    public void copy(StoreOperate source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}