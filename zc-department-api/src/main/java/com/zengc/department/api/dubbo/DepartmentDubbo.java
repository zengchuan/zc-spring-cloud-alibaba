package com.zengc.department.api.dubbo;

import com.zengc.department.api.params.Department;
import com.zengc.department.api.params.DepartmentVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
public interface DepartmentDubbo {

    DepartmentVO addWithEmployee(DepartmentVO departmentVO);

    List<Department> findByOrganization(Long organizationId);

    List<DepartmentVO> findByOrganizationWithEmployees(Long organizationId);

}
