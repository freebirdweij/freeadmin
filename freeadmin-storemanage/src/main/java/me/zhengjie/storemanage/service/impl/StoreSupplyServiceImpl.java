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
package me.zhengjie.storemanage.service.impl;

import me.zhengjie.storemanage.domain.StoreSupply;
import me.zhengjie.exception.EntityExistException;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.storemanage.repository.StoreSupplyRepository;
import me.zhengjie.storemanage.service.StoreSupplyService;
import me.zhengjie.storemanage.service.dto.StoreSupplyDto;
import me.zhengjie.storemanage.service.dto.StoreSupplyQueryCriteria;
import me.zhengjie.storemanage.service.mapstruct.StoreSupplyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author freebirdweij
* @date 2021-02-27
**/
@Service
@RequiredArgsConstructor
public class StoreSupplyServiceImpl implements StoreSupplyService {

    private final StoreSupplyRepository storeSupplyRepository;
    private final StoreSupplyMapper storeSupplyMapper;

    @Override
    public Map<String,Object> queryAll(StoreSupplyQueryCriteria criteria, Pageable pageable){
        Page<StoreSupply> page = storeSupplyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(storeSupplyMapper::toDto));
    }

    @Override
    public List<StoreSupplyDto> queryAll(StoreSupplyQueryCriteria criteria){
        return storeSupplyMapper.toDto(storeSupplyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public StoreSupplyDto findById(Long supplyId) {
        StoreSupply storeSupply = storeSupplyRepository.findById(supplyId).orElseGet(StoreSupply::new);
        ValidationUtil.isNull(storeSupply.getSupplyId(),"StoreSupply","supplyId",supplyId);
        return storeSupplyMapper.toDto(storeSupply);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StoreSupplyDto create(StoreSupply resources) {
        if(storeSupplyRepository.findBySupplyCode(resources.getSupplyCode()) != null){
            throw new EntityExistException(StoreSupply.class,"supply_code",resources.getSupplyCode());
        }
        return storeSupplyMapper.toDto(storeSupplyRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(StoreSupply resources) {
        StoreSupply storeSupply = storeSupplyRepository.findById(resources.getSupplyId()).orElseGet(StoreSupply::new);
        ValidationUtil.isNull( storeSupply.getSupplyId(),"StoreSupply","id",resources.getSupplyId());
        StoreSupply storeSupply1 = null;
        storeSupply1 = storeSupplyRepository.findBySupplyCode(resources.getSupplyCode());
        if(storeSupply1 != null && !storeSupply1.getSupplyId().equals(storeSupply.getSupplyId())){
            throw new EntityExistException(StoreSupply.class,"supply_code",resources.getSupplyCode());
        }
        storeSupply.copy(resources);
        storeSupplyRepository.save(storeSupply);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long supplyId : ids) {
            storeSupplyRepository.deleteById(supplyId);
        }
    }

    @Override
    public void download(List<StoreSupplyDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (StoreSupplyDto storeSupply : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("供应商号", storeSupply.getSupplyCode());
            map.put("供应商名称", storeSupply.getName());
            map.put("联系人", storeSupply.getContact());
            map.put("联系电话", storeSupply.getPhone());
            map.put("传真", storeSupply.getFax());
            map.put("地址", storeSupply.getAddress());
            map.put("邮政编码", storeSupply.getZipcode());
            map.put("网址", storeSupply.getWebadd());
            map.put("邮箱", storeSupply.getEmail());
            map.put("创建者", storeSupply.getCreateBy());
            map.put("更新者", storeSupply.getUpdateBy());
            map.put("创建日期", storeSupply.getCreateTime());
            map.put("更新时间", storeSupply.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}