package com.xw.mallLearning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.generator.mbg.entity.PmsBrand;
import com.xw.mallLearning.generator.mbg.mapper.PmsBrandMapper;
import com.xw.mallLearning.service.PmsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Slf4j
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Override
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(list());
    }

    @Override
    public CommonResult createBrand(PmsBrand pmsBrand) {
        boolean isSuccess = save(pmsBrand);
        if (!isSuccess) {
            log.debug("createBrand failed:{}", pmsBrand);
            return CommonResult.failed("操作失败");
        }
        log.debug("createBrand success:{}", pmsBrand);
        return CommonResult.success(pmsBrand);
    }

    @Override
    public CommonResult updateBrand(PmsBrand pmsBrandDto, BindingResult result) {
        boolean isSuccess = updateById(pmsBrandDto);
        if (!isSuccess) {
            log.debug("updateBrand failed:{}", pmsBrandDto);
            return CommonResult.failed("操作失败");
        }
        log.debug("updateBrand success:{}", pmsBrandDto);
        return CommonResult.success(pmsBrandDto);
    }

    @Override
    public CommonResult deleteBrand(Long id) {
        boolean isSuccess = removeById(id);
        if (!isSuccess) {
            log.debug("deleteBrand failed:{}", id);
            return CommonResult.failed("操作失败");
        }
        log.debug("deleteBrand success:{}", id);
        return CommonResult.success(id);
    }

    @Override
    public CommonResult<List<PmsBrand>> listBrand(Integer current, Integer limit) {
        // 检查参数是否合法
        if (current < 1)
            current = 1;
        if (limit <= 0)
            limit = 3;
        Page<PmsBrand> page = new Page<>(current, limit);
        page(page);
        return CommonResult.success(page.getRecords());
    }

    @Override
    public CommonResult<PmsBrand> brand(Long id) {
        LambdaQueryWrapper<PmsBrand> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PmsBrand::getId, id);
        PmsBrand pmsBrand = getOne(wrapper);
        return CommonResult.success(pmsBrand);
    }
}
