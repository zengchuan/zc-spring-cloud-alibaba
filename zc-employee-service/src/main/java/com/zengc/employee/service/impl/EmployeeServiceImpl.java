package com.zengc.employee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zengc.employee.api.params.Employee;
import com.zengc.employee.mapper.EmployeeMapper;
import com.zengc.employee.service.IEmployeeService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {


    public List<Employee> getEmployees() {
        List<Employee> list = super.list();
        return list;
    }

    public void setEmployees(List<Employee> employees) {
        employees.stream().forEach(employee -> {
            this.add(employee);
        });
    }

    public Employee add(Employee employee) {
        super.save(employee);
        return employee;
    }

    public List<Employee> addList(List<Employee> employeeList) {
        List<Employee> employees = Lists.newArrayList();
        if(employeeList != null && employeeList.size() > 0) {
            employeeList.stream().forEach(employee -> {
                employees.add(this.add(employee));
            });
        }
        return employees;
    }

    public Employee findById(Long id) {
        return super.getById(id);
    }

    public List<Employee> findAll() {
        return getEmployees();
    }


    public List<Employee> findByDepartment(Long departmentId) {
        return super.list(new QueryWrapper<Employee>().eq("department_id", departmentId));
    }

    public List<Employee> findByOrganization(Long organizationId) {
        return super.list(new QueryWrapper<Employee>().eq("organization_id", organizationId));
    }

}
