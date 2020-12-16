package com.zengc.organization.service;

import com.zengc.organization.api.params.Organization;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengc.organization.api.params.OrganizationVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
public interface IOrganizationService extends IService<Organization> {
    Organization add(Organization organization);

    OrganizationVO addWithEmployee(OrganizationVO organizationVO);

    Organization findById(Long id);

    List<Organization> findAll();

    OrganizationVO findByIdWithDepartments(Long id);

    OrganizationVO findByIdWithDepartmentsAndEmployees(Long id) ;


    }
