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
package me.zhengjie.test.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.test.domain.AppProperty;
import me.zhengjie.test.service.AppPropertyService;
import me.zhengjie.test.service.dto.AppPropertyQueryCriteria;
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
* @date 2021-01-26
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "IfProperty管理")
@RequestMapping("/api/appProperty")
public class AppPropertyController {

    private final AppPropertyService appPropertyService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('appProperty:list')")
    public void download(HttpServletResponse response, AppPropertyQueryCriteria criteria) throws IOException {
        appPropertyService.download(appPropertyService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询IfProperty")
    @ApiOperation("查询IfProperty")
    @PreAuthorize("@el.check('appProperty:list')")
    public ResponseEntity<Object> query(AppPropertyQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(appPropertyService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增IfProperty")
    @ApiOperation("新增IfProperty")
    @PreAuthorize("@el.check('appProperty:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody AppProperty resources){
        return new ResponseEntity<>(appPropertyService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改IfProperty")
    @ApiOperation("修改IfProperty")
    @PreAuthorize("@el.check('appProperty:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody AppProperty resources){
        appPropertyService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除IfProperty")
    @ApiOperation("删除IfProperty")
    @PreAuthorize("@el.check('appProperty:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        appPropertyService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}