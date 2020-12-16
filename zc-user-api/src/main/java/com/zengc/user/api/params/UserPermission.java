package com.zengc.user.api.params;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-29
 */
@ApiModel(value = "")
public class UserPermission implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String nameE;
    private String url;

    public Integer getId() {
        return id;
    }

    public UserPermission setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserPermission setName(String name) {
        this.name = name;
        return this;
    }

    public String getNameE() {
        return nameE;
    }

    public UserPermission setNameE(String nameE) {
        this.nameE = nameE;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public UserPermission setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String toString() {
        return "UserPermission{" +
                ", id=" + id +
                ", name=" + name +
                ", nameE=" + nameE +
                ", url=" + url +
                "}";
    }
}