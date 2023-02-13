package com.xw.mallLearning.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.nosql.elasticsearch.document.EsProduct;
import com.xw.mallLearning.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "搜索商品管理")
@RestController
@RequestMapping("/esProduct")
public class EsProductController {
    @Resource
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @PostMapping("/importAll")
    public CommonResult<Integer> importAllList() {
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @GetMapping("/delete/{id}")
    public CommonResult<Object> delete(@PathVariable Long id) {
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id批量删除商品")
    @PostMapping("/delete/batch")
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @PostMapping("/create/{id}")
    public CommonResult<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct != null) {
            return CommonResult.success(esProduct);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "简单搜索")
    @GetMapping("/search/simple")
    public CommonResult<Page<EsProduct>> search(@RequestParam(required = false) String keyword,
                                     @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        org.springframework.data.domain.Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        Page<EsProduct> page = new Page<>();
        page.setTotal(esProductPage.getTotalPages());
        page.setSize(esProductPage.getSize());
        page.setRecords(esProductPage.getContent());

        return CommonResult.success(page);
    }
}
