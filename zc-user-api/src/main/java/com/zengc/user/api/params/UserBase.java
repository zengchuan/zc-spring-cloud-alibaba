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
public class UserBase implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String password;

    public Integer getId() {
        return id;
    }

    public UserBase setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserBase setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserBase setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "UserBase{" +
                ", id=" + id +
                ", name=" + name +
                ", password=" + password +
                "}";
    }
}