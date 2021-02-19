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
package me.zhengjie.test.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author weij
* @date 2021-01-26
**/
@Data
public class AppPropertyDto implements Serializable {

    /** ID */
    private Long propertyId;

    /** 资产名称 */
    private String name;

    /** 资产状态，0-闲置，1-在用，2-报废 */
    private Integer enabled;

    /** 资产类别，0-设备，1-仪器，2-工具，3-家具 */
    private Integer propertyType;

    /** 存放部门 */
    private Long deptId;

    /** 责任人 */
    private Long userId;

    /** 创建者 */
    private String createBy;

    /** 更新者 */
    private String updateBy;

    /** 创建日期 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;
}