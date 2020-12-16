package com.zengc.organization.api.params;

import com.zengc.department.api.params.DepartmentVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
@ApiModel(value = "")
public class OrganizationVO implements Serializable {

    public Organization organization;
    public List<DepartmentVO> departmentVOList;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<DepartmentVO> getDepartmentVOList() {
        return departmentVOList;
    }

    public void setDepartmentVOList(List<DepartmentVO> departmentVOList) {
        this.departmentVOList = departmentVOList;
    }
}