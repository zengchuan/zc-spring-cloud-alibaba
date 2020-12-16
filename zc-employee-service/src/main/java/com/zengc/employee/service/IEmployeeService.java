package com.zengc.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
public interface IEmployeeService extends IService<Employee> {
    List<Employee> getEmployees();

    void setEmployees(List<Employee> employees);

    Employee add(Employee employee);

    List<Employee> addList(List<Employee> employeeList);

    Employee findById(Long id);

    List<Employee> findAll();

    List<Employee> findByDepartment(Long departmentId);

    List<Employee> findByOrganization(Long organizationId);

}
