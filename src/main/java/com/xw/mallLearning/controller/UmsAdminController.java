package com.xw.mallLearning.controller;


import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.dto.UmsAdminLoginParam;
import com.xw.mallLearning.generator.mbg.entity.UmsAdmin;
import com.xw.mallLearning.generator.mbg.entity.UmsPermission;
import com.xw.mallLearning.service.IUmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author xw
 * @since 2023-02-06
 */
@Api(tags = "后台用户管理")
@RestController
@RequestMapping("/admin")
public class UmsAdminController {
    @Resource
    private IUmsAdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult result) {
        return adminService.register(umsAdminParam);
    }

    @ApiOperation("登录以后返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        return adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping("/permission/{adminId}")
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
