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
public class UserVsRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public UserVsRole setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserVsRole setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public UserVsRole setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    @Override
    public String toString() {
        return "UserVsRole{" +
                ", id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                "}";
    }
}