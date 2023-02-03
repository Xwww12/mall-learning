package com.xw.mallLearning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.generator.mbg.entity.PmsBrand;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface PmsBrandService extends IService<PmsBrand> {
    CommonResult<List<PmsBrand>> getBrandList();

    CommonResult createBrand(PmsBrand pmsBrand);

    CommonResult updateBrand(PmsBrand pmsBrandDto, BindingResult result);

    CommonResult deleteBrand(Long id);

    CommonResult<List<PmsBrand>> listBrand(Integer current, Integer limit);

    CommonResult<PmsBrand> brand(Long id);
}
