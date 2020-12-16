package com.zengc.organization.api.params;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
@ApiModel(value = "")
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String address;

    public Long getId() {
        return id;
    }

    public Organization setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Organization setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Organization setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public String toString() {
        return "Organization{" +
                ", id=" + id +
                ", name=" + name +
                ", address=" + address +
                "}";
    }
}