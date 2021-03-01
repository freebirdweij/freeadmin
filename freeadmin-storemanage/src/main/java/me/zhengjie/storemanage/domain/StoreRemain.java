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
import me.zhengjie.storemanage.service.dto.GoodsMiddleDto;
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
@Table(name="store_remain")
public class StoreRemain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "remain_id")
    @ApiModelProperty(value = "ID")
    private Long remainId;

    @Column(name = "store_id",nullable = false)
    @NotNull
    @ApiModelProperty(value = "仓库ID，由字典实现")
    private Long storeId;

    @Column(name = "goods_id",nullable = false)
    @NotNull
    @ApiModelProperty(value = "货物ID")
    private Long goodsId;

    @OneToOne
    @JoinColumn(name = "goods_id",insertable = false,updatable = false)
    @ApiModelProperty(value = "货物")
    private StoreGoods goods;
    
    @Column(name = "counts",nullable = false)
    @NotNull
    @ApiModelProperty(value = "库存数量")
    private Long counts;

    @Column(name = "amount",nullable = false)
    @NotNull
    @ApiModelProperty(value = "库存金额")
    private BigDecimal amount;

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


    public void copy(StoreRemain source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}