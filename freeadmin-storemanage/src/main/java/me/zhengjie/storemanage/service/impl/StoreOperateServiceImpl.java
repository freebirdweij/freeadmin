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

import me.zhengjie.storemanage.domain.StoreOperate;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.storemanage.repository.StoreOperateRepository;
import me.zhengjie.storemanage.service.StoreOperateService;
import me.zhengjie.storemanage.service.dto.StoreOperateDto;
import me.zhengjie.storemanage.service.dto.StoreOperateQueryCriteria;
import me.zhengjie.storemanage.service.mapstruct.StoreOperateMapper;
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
public class StoreOperateServiceImpl implements StoreOperateService {

    private final StoreOperateRepository storeOperateRepository;
    private final StoreOperateMapper storeOperateMapper;

    @Override
    public Map<String,Object> queryAll(StoreOperateQueryCriteria criteria, Pageable pageable){
        Page<StoreOperate> page = storeOperateRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(storeOperateMapper::toDto));
    }

    @Override
    public List<StoreOperateDto> queryAll(StoreOperateQueryCriteria criteria){
        return storeOperateMapper.toDto(storeOperateRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public StoreOperateDto findById(Long operateId) {
        StoreOperate storeOperate = storeOperateRepository.findById(operateId).orElseGet(StoreOperate::new);
        ValidationUtil.isNull(storeOperate.getOperateId(),"StoreOperate","operateId",operateId);
        return storeOperateMapper.toDto(storeOperate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StoreOperateDto create(StoreOperate resources) {
        return storeOperateMapper.toDto(storeOperateRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(StoreOperate resources) {
        StoreOperate storeOperate = storeOperateRepository.findById(resources.getOperateId()).orElseGet(StoreOperate::new);
        ValidationUtil.isNull( storeOperate.getOperateId(),"StoreOperate","id",resources.getOperateId());
        storeOperate.copy(resources);
        storeOperateRepository.save(storeOperate);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long operateId : ids) {
            storeOperateRepository.deleteById(operateId);
        }
    }

    @Override
    public void download(List<StoreOperateDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (StoreOperateDto storeOperate : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("库存ID", storeOperate.getRemainId());
            map.put("与本次操作相关人员", storeOperate.getUserId());
            map.put("操作类型，由字典实现", storeOperate.getOperateType());
            map.put("操作数量", storeOperate.getCounts());
            map.put("操作金额", storeOperate.getAmount());
            map.put("创建者", storeOperate.getCreateBy());
            map.put("更新者", storeOperate.getUpdateBy());
            map.put("创建日期", storeOperate.getCreateTime());
            map.put("更新时间", storeOperate.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}