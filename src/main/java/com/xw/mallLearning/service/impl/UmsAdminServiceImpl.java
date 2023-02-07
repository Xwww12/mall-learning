package com.xw.mallLearning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.generator.mbg.entity.*;
import com.xw.mallLearning.generator.mbg.mapper.UmsAdminMapper;
import com.xw.mallLearning.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.mallLearning.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author xw
 * @since 2023-02-06
 */
@Slf4j
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


//    @Resource
    @Autowired
    private UserDetailsService userDetailsService;

//    @Resource
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    @Resource
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private IUmsAdminRoleRelationService adminRoleRelationService;

    @Resource
    private IUmsRolePermissionRelationService rolePermissionRelationService;

    @Resource
    private IUmsAdminPermissionRelationService adminPermissionRelationService;

    @Resource
    private IUmsPermissionService permissionService;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        LambdaQueryWrapper<UmsAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsAdmin::getUsername, username);
        List<UmsAdmin> list = this.list(wrapper);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        // 获取用户拥有的角色
        LambdaQueryWrapper<UmsAdminRoleRelation> roleWrapper = new LambdaQueryWrapper<>();
        roleWrapper.eq(UmsAdminRoleRelation::getAdminId, adminId);
        List<UmsAdminRoleRelation> adminRoleList = adminRoleRelationService.list(roleWrapper);
        // 获取角色对应的权限id
        List<UmsRolePermissionRelation> rolePermissionList = null;
        LambdaQueryWrapper<UmsRolePermissionRelation> rolePermissionWrapper = new LambdaQueryWrapper<>();
        List<Long> roleIds= adminRoleList.stream().map(UmsAdminRoleRelation::getRoleId).collect(Collectors.toList());
        if (roleIds.size() > 0) {
            rolePermissionWrapper.in(UmsRolePermissionRelation::getRoleId, roleIds);
            rolePermissionList = rolePermissionRelationService.list(rolePermissionWrapper);
        } else {
            rolePermissionList = new ArrayList<>();
        }
        // 获取用户额外权限id
        LambdaQueryWrapper<UmsAdminPermissionRelation> permissionWrapper = new LambdaQueryWrapper<>();
        permissionWrapper.eq(UmsAdminPermissionRelation::getAdminId, adminId);
        List<UmsAdminPermissionRelation> adminPermissionList = adminPermissionRelationService.list(permissionWrapper);
        // 通过权限id查询权限
        ArrayList<Long> permissionIds = new ArrayList<>();
        permissionIds.addAll(rolePermissionList.stream().map(UmsRolePermissionRelation::getPermissionId).collect(Collectors.toList()));
        permissionIds.addAll(adminPermissionList.stream().map(UmsAdminPermissionRelation::getPermissionId).collect(Collectors.toList()));
        // 返回
        if (permissionIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<UmsPermission> res = permissionService.query().in("id", permissionIds).list();
        if (res == null || res.size() == 0) {
            return Collections.emptyList();
        }
        return res;
    }

    @Override
    public CommonResult<UmsAdmin> register(UmsAdmin umsAdminParam) {
        if (query().eq("username", umsAdminParam.getUsername()).list().size() > 0) {
            return CommonResult.failed();
        }
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
        umsAdmin.setStatus(1);
        return CommonResult.success(umsAdmin);
    }

    @Override
    public CommonResult login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

}
