package com.xw.mallLearning.service;

import com.xw.mallLearning.common.api.CommonPage;
import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.generator.mbg.entity.PmsBrand;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface PmsBrandService {
    CommonResult<List<PmsBrand>> getBrandList();

    CommonResult createBrand(PmsBrand pmsBrand);

    CommonResult updateBrand(Long id, PmsBrand pmsBrandDto, BindingResult result);

    CommonResult deleteBrand(Long id);

    CommonResult<CommonPage<PmsBrand>> listBrand(Integer current, Integer limit);

    CommonResult<PmsBrand> brand(Long id);
}
