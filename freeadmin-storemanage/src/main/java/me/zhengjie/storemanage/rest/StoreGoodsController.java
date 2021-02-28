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
import me.zhengjie.storemanage.domain.StoreGoods;
import me.zhengjie.storemanage.service.StoreGoodsService;
import me.zhengjie.storemanage.service.dto.StoreGoodsQueryCriteria;
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
@Api(tags = "货物管理")
@RequestMapping("/api/storeGoods")
public class StoreGoodsController {

    private final StoreGoodsService storeGoodsService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('storeGoods:list')")
    public void download(HttpServletResponse response, StoreGoodsQueryCriteria criteria) throws IOException {
        storeGoodsService.download(storeGoodsService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询货物")
    @ApiOperation("查询货物")
    @PreAuthorize("@el.check('storeGoods:list')")
    public ResponseEntity<Object> query(StoreGoodsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(storeGoodsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增货物")
    @ApiOperation("新增货物")
    @PreAuthorize("@el.check('storeGoods:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody StoreGoods resources){
        return new ResponseEntity<>(storeGoodsService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改货物")
    @ApiOperation("修改货物")
    @PreAuthorize("@el.check('storeGoods:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody StoreGoods resources){
        storeGoodsService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除货物")
    @ApiOperation("删除货物")
    @PreAuthorize("@el.check('storeGoods:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        storeGoodsService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}