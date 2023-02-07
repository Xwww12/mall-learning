package com.xw.mallLearning.generator.mbg.mapper;

import com.xw.mallLearning.generator.mbg.entity.UmsAdminPermissionRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) Mapper 接口
 * </p>
 *
 * @author xw
 * @since 2023-02-06
 */
@Mapper
public interface UmsAdminPermissionRelationMapper extends BaseMapper<UmsAdminPermissionRelation> {

}
