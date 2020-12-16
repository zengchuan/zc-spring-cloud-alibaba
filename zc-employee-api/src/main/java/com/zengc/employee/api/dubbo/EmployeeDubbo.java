package com.zengc.employee.api.dubbo;

import com.zengc.employee.api.params.Employee;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
public interface EmployeeDubbo {

    List<Employee> addList(List<Employee> employeeList);

    List<Employee> findByDepartment(Long departmentId);

    List<Employee> findByOrganization(Long organizationId);

}
