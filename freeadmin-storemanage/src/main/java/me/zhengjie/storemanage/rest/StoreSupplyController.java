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
import me.zhengjie.storemanage.domain.StoreSupply;
import me.zhengjie.storemanage.service.StoreSupplyService;
import me.zhengjie.storemanage.service.dto.StoreSupplyQueryCriteria;
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
@Api(tags = "供应商管理")
@RequestMapping("/api/storeSupply")
public class StoreSupplyController {

    private final StoreSupplyService storeSupplyService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('storeSupply:list')")
    public void download(HttpServletResponse response, StoreSupplyQueryCriteria criteria) throws IOException {
        storeSupplyService.download(storeSupplyService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询供应商")
    @ApiOperation("查询供应商")
    @PreAuthorize("@el.check('storeSupply:list')")
    public ResponseEntity<Object> query(StoreSupplyQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(storeSupplyService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增供应商")
    @ApiOperation("新增供应商")
    @PreAuthorize("@el.check('storeSupply:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody StoreSupply resources){
        return new ResponseEntity<>(storeSupplyService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改供应商")
    @ApiOperation("修改供应商")
    @PreAuthorize("@el.check('storeSupply:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody StoreSupply resources){
        storeSupplyService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除供应商")
    @ApiOperation("删除供应商")
    @PreAuthorize("@el.check('storeSupply:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        storeSupplyService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}