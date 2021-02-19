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
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @website https://el-admin.vip
* @author weij
* @date 2021-01-26
**/
@Data
public class AppPropertyQueryCriteria{

    /** 精确 */
    @Query
    private Long propertyId;

    /** 精确 */
    @Query
    private String name;

    /** 精确 */
    @Query
    private Integer enabled;

    /** 精确 */
    @Query
    private Integer propertyType;

    /** 精确 */
    @Query
    private Long deptId;

    /** 精确 */
    @Query
    private Long userId;
}