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
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public UserRole setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserRole setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                ", id=" + id +
                ", name=" + name +
                "}";
    }
}