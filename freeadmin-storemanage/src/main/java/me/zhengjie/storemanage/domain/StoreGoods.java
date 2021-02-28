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
@Data
@Table(name="store_goods")
public class StoreGoods implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    @ApiModelProperty(value = "ID")
    private Long goodsId;

    @Column(name = "goods_code",unique = true,nullable = false)
    @NotNull
    @ApiModelProperty(value = "商品号")
    private String goodsCode;

    @Column(name = "name",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "货物名称")
    private String name;

    @Column(name = "unit",nullable = false)
    @NotNull
    @ApiModelProperty(value = "单位，由字典实现")
    private Integer unit;

    @Column(name = "supply_id",nullable = false)
    @NotNull
    @ApiModelProperty(value = "供应商")
    private Long supplyId;

    @Column(name = "price",nullable = false)
    @NotNull
    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @Column(name = "goods_type")
    @ApiModelProperty(value = "货物类别，由字典实现")
    private Integer goodsType;

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

    public void copy(StoreGoods source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}