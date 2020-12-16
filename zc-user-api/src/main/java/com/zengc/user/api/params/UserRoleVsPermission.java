package com.zengc.user.api.params;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-20
 */
@ApiModel(value = "")
public class UserRoleVsPermission implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer roleId;
    private Integer permissionId;

    public Integer getId() {
        return id;
    }

    public UserRoleVsPermission setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public UserRoleVsPermission setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public UserRoleVsPermission setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
        return this;
    }

    @Override
    public String toString() {
        return "UserRoleVsPermission{" +
                ", id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                "}";
    }
}