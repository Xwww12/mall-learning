package com.xw.mallLearning.service.impl;

import com.xw.mallLearning.generator.mbg.entity.UmsAdminPermissionRelation;
import com.xw.mallLearning.generator.mbg.mapper.UmsAdminPermissionRelationMapper;
import com.xw.mallLearning.service.IUmsAdminPermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @author xw
 * @since 2023-02-06
 */
@Service
public class UmsAdminPermissionRelationServiceImpl extends ServiceImpl<UmsAdminPermissionRelationMapper, UmsAdminPermissionRelation> implements IUmsAdminPermissionRelationService {

}
