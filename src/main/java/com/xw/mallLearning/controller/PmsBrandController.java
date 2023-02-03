package com.xw.mallLearning.controller;

import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.generator.mbg.entity.PmsBrand;
import com.xw.mallLearning.service.PmsBrandService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌管理Controller
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Resource
    private PmsBrandService pmsBrandService;

    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> getBrandList() {
        return pmsBrandService.getBrandList();
    }

    @PostMapping("/create")
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        return pmsBrandService.createBrand(pmsBrand);
    }

    @PutMapping("/update/{id}")
    public CommonResult updateBrand(@RequestBody PmsBrand pmsBrandDto,
                                    BindingResult result) {
        return pmsBrandService.updateBrand(pmsBrandDto, result);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        return pmsBrandService.deleteBrand(id);
    }

    @GetMapping("/list")
    public CommonResult<List<PmsBrand>> listBrand(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                                        @RequestParam(value = "limit", defaultValue = "3") Integer limit) {
        return pmsBrandService.listBrand(current, limit);
    }

    @GetMapping("/{id}")
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return pmsBrandService.brand(id);
    }
}
