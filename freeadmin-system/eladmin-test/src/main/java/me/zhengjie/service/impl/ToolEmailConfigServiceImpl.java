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
package me.zhengjie.service.impl;

import me.zhengjie.domain.ToolEmailConfig;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.ToolEmailConfigRepository;
import me.zhengjie.service.ToolEmailConfigService;
import me.zhengjie.service.dto.ToolEmailConfigDto;
import me.zhengjie.service.dto.ToolEmailConfigQueryCriteria;
import me.zhengjie.service.mapstruct.ToolEmailConfigMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
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
* @date 2021-01-25
**/
@Service
@RequiredArgsConstructor
public class ToolEmailConfigServiceImpl implements ToolEmailConfigService {

    private final ToolEmailConfigRepository toolEmailConfigRepository;
    private final ToolEmailConfigMapper toolEmailConfigMapper;

    @Override
    public Map<String,Object> queryAll(ToolEmailConfigQueryCriteria criteria, Pageable pageable){
        Page<ToolEmailConfig> page = toolEmailConfigRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(toolEmailConfigMapper::toDto));
    }

    @Override
    public List<ToolEmailConfigDto> queryAll(ToolEmailConfigQueryCriteria criteria){
        return toolEmailConfigMapper.toDto(toolEmailConfigRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ToolEmailConfigDto findById(Long configId) {
        ToolEmailConfig toolEmailConfig = toolEmailConfigRepository.findById(configId).orElseGet(ToolEmailConfig::new);
        ValidationUtil.isNull(toolEmailConfig.getConfigId(),"ToolEmailConfig","configId",configId);
        return toolEmailConfigMapper.toDto(toolEmailConfig);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ToolEmailConfigDto create(ToolEmailConfig resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setConfigId(snowflake.nextId()); 
        return toolEmailConfigMapper.toDto(toolEmailConfigRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ToolEmailConfig resources) {
        ToolEmailConfig toolEmailConfig = toolEmailConfigRepository.findById(resources.getConfigId()).orElseGet(ToolEmailConfig::new);
        ValidationUtil.isNull( toolEmailConfig.getConfigId(),"ToolEmailConfig","id",resources.getConfigId());
        toolEmailConfig.copy(resources);
        toolEmailConfigRepository.save(toolEmailConfig);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long configId : ids) {
            toolEmailConfigRepository.deleteById(configId);
        }
    }

    @Override
    public void download(List<ToolEmailConfigDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ToolEmailConfigDto toolEmailConfig : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("收件人", toolEmailConfig.getFromUser());
            map.put("邮件服务器SMTP地址", toolEmailConfig.getHost());
            map.put("密码", toolEmailConfig.getPass());
            map.put("端口", toolEmailConfig.getPort());
            map.put("发件者用户名", toolEmailConfig.getUser());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}