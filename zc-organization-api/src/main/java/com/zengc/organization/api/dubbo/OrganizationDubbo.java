package com.zengc.organization.api.dubbo;

import com.zengc.organization.api.params.OrganizationVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
public interface OrganizationDubbo {
    OrganizationVO findByIdWithDepartmentsAndEmployees(Long id) ;
}
