package com.xw.mallLearning.service.impl;

import com.xw.mallLearning.common.api.CommonPage;
import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.generator.mbg.entity.PmsBrand;
import com.xw.mallLearning.generator.mbg.mapper.PmsBrandMapper;
import com.xw.mallLearning.service.PmsBrandService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Resource
    private PmsBrandMapper brandMapper;

    @Override
    public CommonResult<List<PmsBrand>> getBrandList() {
        return null;
    }

    @Override
    public CommonResult createBrand(PmsBrand pmsBrand) {
        return null;
    }

    @Override
    public CommonResult updateBrand(Long id, PmsBrand pmsBrandDto, BindingResult result) {
        return null;
    }

    @Override
    public CommonResult deleteBrand(Long id) {
        return null;
    }

    @Override
    public CommonResult<CommonPage<PmsBrand>> listBrand(Integer current, Integer limit) {
        return null;
    }

    @Override
    public CommonResult<PmsBrand> brand(Long id) {
        return null;
    }
}
