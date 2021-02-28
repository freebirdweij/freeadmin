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
import java.math.BigDecimal;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @website https://el-admin.vip
* @author freebirdweij
* @date 2021-02-27
**/
@Data
public class StoreGoodsQueryCriteria{

    /** 精确 */
    @Query
    private Long goodsId;

    /** 精确 */
    @Query
    private Long goodsCode;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    /** 精确 */
    @Query
    private Integer unit;

    /** 精确 */
    @Query
    private Long supplyId;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private BigDecimal price;

    /** 精确 */
    @Query
    private Integer goodsType;
}