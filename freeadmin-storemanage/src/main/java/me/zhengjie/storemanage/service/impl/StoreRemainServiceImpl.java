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

import me.zhengjie.storemanage.domain.StoreRemain;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.storemanage.repository.StoreRemainRepository;
import me.zhengjie.storemanage.service.StoreRemainService;
import me.zhengjie.storemanage.service.dto.StoreRemainDto;
import me.zhengjie.storemanage.service.dto.StoreRemainQueryCriteria;
import me.zhengjie.storemanage.service.mapstruct.StoreRemainMapper;
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
public class StoreRemainServiceImpl implements StoreRemainService {

    private final StoreRemainRepository storeRemainRepository;
    private final StoreRemainMapper storeRemainMapper;

    @Override
    public Map<String,Object> queryAll(StoreRemainQueryCriteria criteria, Pageable pageable){
        Page<StoreRemain> page = storeRemainRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(storeRemainMapper::toDto));
    }

    @Override
    public List<StoreRemainDto> queryAll(StoreRemainQueryCriteria criteria){
        return storeRemainMapper.toDto(storeRemainRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public StoreRemainDto findById(Long remainId) {
        StoreRemain storeRemain = storeRemainRepository.findById(remainId).orElseGet(StoreRemain::new);
        ValidationUtil.isNull(storeRemain.getRemainId(),"StoreRemain","remainId",remainId);
        return storeRemainMapper.toDto(storeRemain);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StoreRemainDto create(StoreRemain resources) {
        return storeRemainMapper.toDto(storeRemainRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(StoreRemain resources) {
        StoreRemain storeRemain = storeRemainRepository.findById(resources.getRemainId()).orElseGet(StoreRemain::new);
        ValidationUtil.isNull( storeRemain.getRemainId(),"StoreRemain","id",resources.getRemainId());
        storeRemain.copy(resources);
        storeRemainRepository.save(storeRemain);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long remainId : ids) {
            storeRemainRepository.deleteById(remainId);
        }
    }

    @Override
    public void download(List<StoreRemainDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (StoreRemainDto storeRemain : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("仓库ID，由字典实现", storeRemain.getStoreId());
            map.put("货物ID", storeRemain.getGoodsId());
            map.put("库存数量", storeRemain.getCounts());
            map.put("库存金额", storeRemain.getAmount());
            map.put("创建者", storeRemain.getCreateBy());
            map.put("更新者", storeRemain.getUpdateBy());
            map.put("创建日期", storeRemain.getCreateTime());
            map.put("更新时间", storeRemain.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}