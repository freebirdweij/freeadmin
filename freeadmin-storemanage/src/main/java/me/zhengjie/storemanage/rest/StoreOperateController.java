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
package me.zhengjie.storemanage.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.storemanage.domain.StoreOperate;
import me.zhengjie.storemanage.service.StoreOperateService;
import me.zhengjie.storemanage.service.dto.StoreOperateQueryCriteria;
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
* @author freebirdweij
* @date 2021-02-27
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "仓库操作管理")
@RequestMapping("/api/storeOperate")
public class StoreOperateController {

    private final StoreOperateService storeOperateService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('storeOperate:list')")
    public void download(HttpServletResponse response, StoreOperateQueryCriteria criteria) throws IOException {
        storeOperateService.download(storeOperateService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询仓库操作")
    @ApiOperation("查询仓库操作")
    @PreAuthorize("@el.check('storeOperate:list')")
    public ResponseEntity<Object> query(StoreOperateQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(storeOperateService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增仓库操作")
    @ApiOperation("新增仓库操作")
    @PreAuthorize("@el.check('storeOperate:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody StoreOperate resources){
        return new ResponseEntity<>(storeOperateService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改仓库操作")
    @ApiOperation("修改仓库操作")
    @PreAuthorize("@el.check('storeOperate:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody StoreOperate resources){
        storeOperateService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除仓库操作")
    @ApiOperation("删除仓库操作")
    @PreAuthorize("@el.check('storeOperate:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        storeOperateService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}