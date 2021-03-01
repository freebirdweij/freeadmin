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
package me.zhengjie.test.service.impl;

import me.zhengjie.test.domain.AppProperty;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.test.repository.AppPropertyRepository;
import me.zhengjie.test.service.AppPropertyService;
import me.zhengjie.test.service.dto.AppPropertyDto;
import me.zhengjie.test.service.dto.AppPropertyQueryCriteria;
import me.zhengjie.test.service.mapstruct.AppPropertyMapper;
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
* @author weij
* @date 2021-01-26
**/
@Service
@RequiredArgsConstructor
public class AppPropertyServiceImpl implements AppPropertyService {

    private final AppPropertyRepository appPropertyRepository;
    private final AppPropertyMapper appPropertyMapper;

    @Override
    public Map<String,Object> queryAll(AppPropertyQueryCriteria criteria, Pageable pageable){
        Page<AppProperty> page = appPropertyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(appPropertyMapper::toDto));
    }

    @Override
    public List<AppPropertyDto> queryAll(AppPropertyQueryCriteria criteria){
        return appPropertyMapper.toDto(appPropertyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public AppPropertyDto findById(Long propertyId) {
        AppProperty appProperty = appPropertyRepository.findById(propertyId).orElseGet(AppProperty::new);
        ValidationUtil.isNull(appProperty.getPropertyId(),"AppProperty","propertyId",propertyId);
        return appPropertyMapper.toDto(appProperty);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppPropertyDto create(AppProperty resources) {
        return appPropertyMapper.toDto(appPropertyRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AppProperty resources) {
        AppProperty appProperty = appPropertyRepository.findById(resources.getPropertyId()).orElseGet(AppProperty::new);
        ValidationUtil.isNull( appProperty.getPropertyId(),"AppProperty","id",resources.getPropertyId());
        appProperty.copy(resources);
        appPropertyRepository.save(appProperty);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long propertyId : ids) {
            appPropertyRepository.deleteById(propertyId);
        }
    }

    @Override
    public void download(List<AppPropertyDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (AppPropertyDto appProperty : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("资产名称", appProperty.getName());
            map.put("资产状态，0-闲置，1-在用，2-报废", appProperty.enableToString());
            map.put("资产类别，0-设备，1-仪器，2-工具，3-家具", appProperty.propertyTypeToString());
            map.put("存放部门", appProperty.getDept().getName());
            map.put("责任人", appProperty.getUser() == null ? "" : appProperty.getUser().getUsername());
            map.put("创建者", appProperty.getCreateBy());
            map.put("更新者", appProperty.getUpdateBy());
            map.put("创建日期", appProperty.getCreateTime());
            map.put("更新时间", appProperty.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}