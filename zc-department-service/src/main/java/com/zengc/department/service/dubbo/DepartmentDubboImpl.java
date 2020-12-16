package com.zengc.department.service.dubbo;

import com.zengc.department.api.params.Department;
import com.zengc.department.api.params.DepartmentVO;
import com.zengc.department.api.dubbo.DepartmentDubbo;
import com.zengc.department.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
@org.apache.dubbo.config.annotation.Service
@org.springframework.stereotype.Service
public class DepartmentDubboImpl implements DepartmentDubbo {

    @Autowired
    private IDepartmentService departmentService;

    public DepartmentVO addWithEmployee(DepartmentVO departmentVO) {
        return departmentService.addWithEmployee(departmentVO);
    }

    public List<Department> findByOrganization(Long organizationId) {
        return departmentService.findByOrganization(organizationId);
    }

    public List<DepartmentVO> findByOrganizationWithEmployees(Long organizationId) {
        return departmentService.findByOrganizationWithEmployees(organizationId);
    }
}
