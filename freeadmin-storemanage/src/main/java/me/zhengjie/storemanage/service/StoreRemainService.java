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

import me.zhengjie.storemanage.domain.StoreRemain;
import me.zhengjie.storemanage.service.dto.StoreRemainDto;
import me.zhengjie.storemanage.service.dto.StoreRemainQueryCriteria;
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
public interface StoreRemainService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(StoreRemainQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<StoreRemainDto>
    */
    List<StoreRemainDto> queryAll(StoreRemainQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param remainId ID
     * @return StoreRemainDto
     */
    StoreRemainDto findById(Long remainId);

    /**
    * 创建
    * @param resources /
    * @return StoreRemainDto
    */
    StoreRemainDto create(StoreRemain resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(StoreRemain resources);
    
    /**
    * 编辑
    * @param resources /
    */
    void ingood(StoreRemain resources);
    
    /**
    * 编辑
    * @param resources /
    */
    void outgood(StoreRemain resources);
    /**
    * 编辑
    * @param resources /
    */
    void refundgood(StoreRemain resources);
    
    /**
    * 编辑
    * @param resources /
    */
    void takegood(StoreRemain resources);

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
    void download(List<StoreRemainDto> all, HttpServletResponse response) throws IOException;
}