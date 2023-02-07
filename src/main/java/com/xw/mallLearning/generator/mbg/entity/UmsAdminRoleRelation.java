package com.xw.mallLearning.generator.mbg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 后台用户和角色关系表
 * </p>
 *
 * @author xw
 * @since 2023-02-06
 */
@TableName("ums_admin_role_relation")
@ApiModel(value = "UmsAdminRoleRelation对象", description = "后台用户和角色关系表")
public class UmsAdminRoleRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("admin_id")
    private Long adminId;

    @TableField("role_id")
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UmsAdminRoleRelation{" +
            "id=" + id +
            ", adminId=" + adminId +
            ", roleId=" + roleId +
        "}";
    }
}
