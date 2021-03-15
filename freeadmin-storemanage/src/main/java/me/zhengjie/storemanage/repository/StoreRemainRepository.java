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
package me.zhengjie.storemanage.repository;

import java.util.Optional;
import java.util.Set;

import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.storemanage.domain.StoreRemain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
* @website https://el-admin.vip
* @author freebirdweij
* @date 2021-02-27
**/
public interface StoreRemainRepository extends JpaRepository<StoreRemain, Long>, JpaSpecificationExecutor<StoreRemain> {
    /**
     * 根据角色ID 查询
     * @param roleId 角色ID
     * @return /
     */
    @Query(value = "select * from store_remain where " +
            "store_id = ?1 and goods_id = ?2", nativeQuery = true)
    StoreRemain findByStoreAndGoods(Long sotreId,Long goodsId);

}