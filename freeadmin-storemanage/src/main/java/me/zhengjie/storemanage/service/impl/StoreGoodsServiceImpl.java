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

import me.zhengjie.storemanage.domain.StoreGoods;
import me.zhengjie.exception.EntityExistException;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.storemanage.repository.StoreGoodsRepository;
import me.zhengjie.storemanage.service.StoreGoodsService;
import me.zhengjie.storemanage.service.dto.StoreGoodsDto;
import me.zhengjie.storemanage.service.dto.StoreGoodsQueryCriteria;
import me.zhengjie.storemanage.service.mapstruct.StoreGoodsMapper;
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
public class StoreGoodsServiceImpl implements StoreGoodsService {

    private final StoreGoodsRepository storeGoodsRepository;
    private final StoreGoodsMapper storeGoodsMapper;

    @Override
    public Map<String,Object> queryAll(StoreGoodsQueryCriteria criteria, Pageable pageable){
        Page<StoreGoods> page = storeGoodsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(storeGoodsMapper::toDto));
    }

    @Override
    public List<StoreGoodsDto> queryAll(StoreGoodsQueryCriteria criteria){
        return storeGoodsMapper.toDto(storeGoodsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public StoreGoodsDto findById(Long goodsId) {
        StoreGoods storeGoods = storeGoodsRepository.findById(goodsId).orElseGet(StoreGoods::new);
        ValidationUtil.isNull(storeGoods.getGoodsId(),"StoreGoods","goodsId",goodsId);
        return storeGoodsMapper.toDto(storeGoods);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StoreGoodsDto create(StoreGoods resources) {
        if(storeGoodsRepository.findByGoodsCode(resources.getGoodsCode()) != null){
            throw new EntityExistException(StoreGoods.class,"goods_code",resources.getGoodsCode());
        }
        return storeGoodsMapper.toDto(storeGoodsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(StoreGoods resources) {
        StoreGoods storeGoods = storeGoodsRepository.findById(resources.getGoodsId()).orElseGet(StoreGoods::new);
        ValidationUtil.isNull( storeGoods.getGoodsId(),"StoreGoods","id",resources.getGoodsId());
        StoreGoods storeGoods1 = null;
        storeGoods1 = storeGoodsRepository.findByGoodsCode(resources.getGoodsCode());
        if(storeGoods1 != null && !storeGoods1.getGoodsId().equals(storeGoods.getGoodsId())){
            throw new EntityExistException(StoreGoods.class,"goods_code",resources.getGoodsCode());
        }
        storeGoods.copy(resources);
        storeGoodsRepository.save(storeGoods);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long goodsId : ids) {
            storeGoodsRepository.deleteById(goodsId);
        }
    }

    @Override
    public void download(List<StoreGoodsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (StoreGoodsDto storeGoods : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("商品号", storeGoods.getGoodsCode());
            map.put("货物名称", storeGoods.getName());
            map.put("单位，由字典实现", storeGoods.getUnit());
            map.put("供应商", storeGoods.getSupplyId());
            map.put("单价", storeGoods.getPrice());
            map.put("货物类别，由字典实现", storeGoods.getGoodsType());
            map.put("创建者", storeGoods.getCreateBy());
            map.put("更新者", storeGoods.getUpdateBy());
            map.put("创建日期", storeGoods.getCreateTime());
            map.put("更新时间", storeGoods.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}