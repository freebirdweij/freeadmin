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
package me.zhengjie.storemanage.service;

import me.zhengjie.storemanage.domain.StoreSupply;
import me.zhengjie.storemanage.service.dto.StoreSupplyDto;
import me.zhengjie.storemanage.service.dto.StoreSupplyQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @description 服务接口
* @author freebirdweij
* @date 2021-02-27
**/
public interface StoreSupplyService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(StoreSupplyQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<StoreSupplyDto>
    */
    List<StoreSupplyDto> queryAll(StoreSupplyQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param supplyId ID
     * @return StoreSupplyDto
     */
    StoreSupplyDto findById(Long supplyId);

    /**
    * 创建
    * @param resources /
    * @return StoreSupplyDto
    */
    StoreSupplyDto create(StoreSupply resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(StoreSupply resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<StoreSupplyDto> all, HttpServletResponse response) throws IOException;
}