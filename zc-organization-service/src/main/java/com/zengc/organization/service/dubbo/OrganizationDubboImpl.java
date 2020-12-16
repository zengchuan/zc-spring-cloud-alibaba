package com.zengc.organization.service.dubbo;

import com.zengc.organization.api.params.OrganizationVO;
import com.zengc.organization.api.dubbo.OrganizationDubbo;
import com.zengc.organization.service.IOrganizationService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
@Service
public class OrganizationDubboImpl implements OrganizationDubbo {

    @Autowired
    IOrganizationService organizationService;

    public OrganizationVO findByIdWithDepartmentsAndEmployees(Long id) {
        return organizationService.findByIdWithDepartmentsAndEmployees(id);
    }
}
