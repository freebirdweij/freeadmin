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
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseDTO;
import me.zhengjie.modules.system.service.dto.DeptSmallDto;
import me.zhengjie.modules.system.service.dto.JobSmallDto;
import me.zhengjie.modules.system.service.dto.RoleSmallDto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
* @website https://el-admin.vip
* @description /
* @author weij
* @date 2021-01-26
**/
@Getter
@Setter
public class AppPropertyDto extends BaseDTO implements Serializable {

    /** ID */
    private Long propertyId;

    /** 资产名称 */
    private String name;

    /** 资产状态，0-闲置，1-在用，2-报废 */    
    private Integer enabled;
    
    public String enableToString() {
    	switch(enabled) {
    	case 0 : 
    		return "闲置";
    	case 1:
    		return "在用";
    	case 2:
    		return "报废";
    	default:
    		return "闲置";
    	
        }
    }

	/** 资产类别，0-设备，1-仪器，2-工具，3-家具 */
    private Integer propertyType;
    
    public String propertyTypeToString() {
    	switch(propertyType) {
    	case 0 : 
    		return "设备";
    	case 1:
    		return "仪器";
    	case 2:
    		return "工具";
    	case 3:
    		return "家具";
    	default:
    		return "其他";
    	
        }
    }

    private DeptSmallDto dept;
    
    /** 存放部门 */
    private Long deptId;

    private UserSmallDto user;
    
    /** 责任人 */
    private Long userId;

}