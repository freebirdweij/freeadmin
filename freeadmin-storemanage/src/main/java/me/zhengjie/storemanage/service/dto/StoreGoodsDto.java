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
package me.zhengjie.storemanage.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author freebirdweij
* @date 2021-02-27
**/
@Getter
@Setter
public class StoreGoodsDto implements Serializable {

    /** ID */
    private Long goodsId;

    /** 商品号 */
    private String goodsCode;

    /** 货物名称 */
    private String name;

    /** 单位，由字典实现 */
    private Integer unit;

    /** 供应商 */
    private Long supplyId;

    private SupplySmallDto supply;
    
    /** 单价 */
    private BigDecimal price;

    /** 货物类别，由字典实现 */
    private Integer goodsType;

    /** 创建者 */
    private String createBy;

    /** 更新者 */
    private String updateBy;

    /** 创建日期 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;
}