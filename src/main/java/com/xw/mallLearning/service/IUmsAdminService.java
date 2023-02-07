package com.xw.mallLearning.service;

import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.generator.mbg.entity.UmsAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.mallLearning.generator.mbg.entity.UmsPermission;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author xw
 * @since 2023-02-06
 */
public interface IUmsAdminService extends IService<UmsAdmin> {

    UmsAdmin getAdminByUsername(String username);

    List<UmsPermission> getPermissionList(Long id);

    CommonResult<UmsAdmin> register(UmsAdmin umsAdminParam);

    CommonResult login(String username, String password);
}
