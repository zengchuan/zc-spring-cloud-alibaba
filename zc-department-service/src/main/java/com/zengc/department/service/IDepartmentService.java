package com.zengc.department.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
public interface IDepartmentService  {
    Department add(Department department);

    DepartmentVO addWithEmployee(DepartmentVO departmentVO);

    DepartmentVO findById(Long id);

     List<Department> findAll();

    List<Department> findByOrganization(Long organizationId);

    List<DepartmentVO> findByOrganizationWithEmployees(Long organizationId);

}
