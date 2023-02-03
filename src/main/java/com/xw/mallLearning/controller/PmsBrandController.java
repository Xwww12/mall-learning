package com.xw.mallLearning.controller;

import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.generator.mbg.entity.PmsBrand;
import com.xw.mallLearning.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌管理Controller
 */
@Api(tags = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Resource
    private PmsBrandService pmsBrandService;

    @ApiOperation("获取所有品牌列表")
    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> getBrandList() {
        return pmsBrandService.getBrandList();
    }

    @ApiOperation("添加品牌")
    @PostMapping("/create")
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        return pmsBrandService.createBrand(pmsBrand);
    }

    @ApiOperation("更新指定id品牌信息")
    @PutMapping("/update/{id}")
    public CommonResult updateBrand(@RequestBody PmsBrand pmsBrandDto,
                                    BindingResult result) {
        return pmsBrandService.updateBrand(pmsBrandDto, result);
    }

    @ApiOperation("删除指定id的品牌")
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        return pmsBrandService.deleteBrand(id);
    }

    @ApiOperation("分页查询品牌列表")
    @GetMapping("/list")
    public CommonResult<List<PmsBrand>> listBrand(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                                        @RequestParam(value = "limit", defaultValue = "3") Integer limit) {
        return pmsBrandService.listBrand(current, limit);
    }

    @ApiOperation("获取指定id的品牌详情")
    @GetMapping("/{id}")
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return pmsBrandService.brand(id);
    }
}
