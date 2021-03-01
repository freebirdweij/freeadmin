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
import me.zhengjie.base.BaseDTO;

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
public class StoreRemainDto extends BaseDTO implements Serializable {

    /** ID */
    private Long remainId;

    /** 仓库ID，由字典实现 */
    private Long storeId;

    /** 货物ID */
    private Long goodsId;
    
    private GoodsMiddleDto goods;

    /** 库存数量 */
    private Long counts;

    /** 库存金额 */
    private BigDecimal amount;

}