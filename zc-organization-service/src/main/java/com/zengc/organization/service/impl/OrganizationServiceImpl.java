package com.zengc.organization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zengc.core.exception.ZCException;
import com.zengc.department.api.dubbo.DepartmentDubbo;
import com.zengc.department.api.feign.DepartmentFeign;
import com.zengc.department.api.params.Department;
import com.zengc.department.api.params.DepartmentVO;
import com.zengc.organization.api.params.Organization;
import com.zengc.organization.api.params.OrganizationVO;
import com.zengc.organization.mapper.OrganizationMapper;
import com.zengc.organization.service.IOrganizationService;
import org.apache.commons.compress.utils.Lists;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements IOrganizationService {

    @Reference
    private DepartmentDubbo departmentService;
//    @Autowired
//    private DepartmentFeign departmentService;

    public Organization add(Organization organization) {
        super.save(organization);
        return organization;
    }

    public OrganizationVO addWithEmployee(OrganizationVO organizationVO) {
        Organization organization = this.add(organizationVO.getOrganization());
        organizationVO.setOrganization(organization);
        List<DepartmentVO> departmentVOList = organizationVO.getDepartmentVOList();
        if (departmentVOList != null && departmentVOList.size() > 0) {
            List<DepartmentVO> departmentVOs = Lists.newArrayList();
            departmentVOList.stream().forEach(departmentVO -> {
                departmentVO.getDepartment().setOrganizationId(organization.getId());
                DepartmentVO departmentVO1 = departmentService.addWithEmployee(departmentVO);
                departmentVOs.add(departmentVO1);
            });
            organizationVO.setDepartmentVOList(departmentVOs);
        }

        return organizationVO;
    }

    public Organization findById(Long id) {
        return getById(id);
    }

    public List<Organization> findAll() {
        return list();
    }

    public OrganizationVO findByIdWithDepartments(Long id) {
        OrganizationVO organizationVO = null;
        Organization organization = findById(id);
        if (organization != null) {
            organizationVO = new OrganizationVO();
            List<Department> departmentList = departmentService.findByOrganization(organization.getId());
            List<DepartmentVO> departmentVOList = Lists.newArrayList();
            departmentList.stream().forEach(department -> {
                DepartmentVO departmentVO = new DepartmentVO();
                departmentVO.setDepartment(department);
                departmentVOList.add(departmentVO);
            });

            organizationVO.setOrganization(organization);
            organizationVO.setDepartmentVOList(departmentVOList);
        }
        return organizationVO;
    }

    public OrganizationVO findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
        OrganizationVO organizationVO = null;
        Organization organization = findById(id);
        if (organization != null) {
            organizationVO = new OrganizationVO();
            organizationVO.setOrganization(organization);
            try {
                List<DepartmentVO> departmentVOList = departmentService.findByOrganizationWithEmployees(organization.getId());
                organizationVO.setDepartmentVOList(departmentVOList);
            } catch (Exception e) {
                throw new ZCException("调用departmentService失败：" + e.getMessage());
            }
        }
        return organizationVO;
    }
}
