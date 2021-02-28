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
import me.zhengjie.storemanage.domain.StoreRemain;
import me.zhengjie.storemanage.service.StoreRemainService;
import me.zhengjie.storemanage.service.dto.StoreRemainQueryCriteria;
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
@Api(tags = "库存管理")
@RequestMapping("/api/storeBuy")
public class StoreBuyController {

    private final StoreRemainService storeRemainService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('storeRemain:list')")
    public void download(HttpServletResponse response, StoreRemainQueryCriteria criteria) throws IOException {
        storeRemainService.download(storeRemainService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询库存")
    @ApiOperation("查询库存")
    @PreAuthorize("@el.check('storeRemain:list')")
    public ResponseEntity<Object> query(StoreRemainQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(storeRemainService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增库存")
    @ApiOperation("新增库存")
    @PreAuthorize("@el.check('storeRemain:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody StoreRemain resources){
        return new ResponseEntity<>(storeRemainService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改库存")
    @ApiOperation("修改库存")
    @PreAuthorize("@el.check('storeRemain:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody StoreRemain resources){
        storeRemainService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除库存")
    @ApiOperation("删除库存")
    @PreAuthorize("@el.check('storeRemain:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        storeRemainService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}