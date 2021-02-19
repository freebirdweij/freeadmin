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
package me.zhengjie.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.domain.ToolEmailConfig;
import me.zhengjie.service.ToolEmailConfigService;
import me.zhengjie.service.dto.ToolEmailConfigQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author weij
* @date 2021-01-25
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "Ifemail管理")
@RequestMapping("/api/toolEmailConfig")
public class ToolEmailConfigController {

    private final ToolEmailConfigService toolEmailConfigService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('toolEmailConfig:list')")
    public void download(HttpServletResponse response, ToolEmailConfigQueryCriteria criteria) throws IOException {
        toolEmailConfigService.download(toolEmailConfigService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询Ifemail")
    @ApiOperation("查询Ifemail")
    @PreAuthorize("@el.check('toolEmailConfig:list')")
    public ResponseEntity<Object> query(ToolEmailConfigQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(toolEmailConfigService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增Ifemail")
    @ApiOperation("新增Ifemail")
    @PreAuthorize("@el.check('toolEmailConfig:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ToolEmailConfig resources){
        return new ResponseEntity<>(toolEmailConfigService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改Ifemail")
    @ApiOperation("修改Ifemail")
    @PreAuthorize("@el.check('toolEmailConfig:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ToolEmailConfig resources){
        toolEmailConfigService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除Ifemail")
    @ApiOperation("删除Ifemail")
    @PreAuthorize("@el.check('toolEmailConfig:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        toolEmailConfigService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}