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
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author freebirdweij
* @date 2021-02-27
**/
@Data
public class StoreOperateDto implements Serializable {

    /** ID */
    private Long operateId;

    /** 库存ID */
    private Long remainId;

    /** 与本次操作相关人员 */
    private Long userId;

    /** 操作类型，由字典实现 */
    private Integer operateType;

    /** 操作数量 */
    private Long counts;

    /** 操作金额 */
    private BigDecimal amount;

    /** 创建者 */
    private String createBy;

    /** 更新者 */
    private String updateBy;

    /** 创建日期 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;
    
    public static final Integer INGOODS = 1;
    public static final Integer OUTGOODS = 2;
    public static final Integer TAKEGOODS = 3;
    public static final Integer REFUNDGOODS = 4;
    public static final Integer MOVEGOODSOUT = 5;
    public static final Integer MOVEGOODSIN = 6;
    public static final Integer MODIFYGOODS = 7;

}