package com.zengc.employee.service.dubbo;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zengc.employee.api.params.Employee;
import com.zengc.employee.api.dubbo.EmployeeDubbo;
import com.zengc.employee.mapper.EmployeeMapper;
import com.zengc.employee.service.IEmployeeService;
import org.apache.dubbo.config.annotation.Service;
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
@Service
public class EmployeeDubboImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeDubbo {

    @Autowired
    IEmployeeService employeeService;

    public List<Employee> addList(List<Employee> employeeList) {
        return employeeService.addList(employeeList);
    }

    public List<Employee> findByDepartment(Long departmentId) {
        return employeeService.findByDepartment(departmentId);
    }

    public List<Employee> findByOrganization(Long organizationId) {
        return employeeService.findByOrganization(organizationId);
    }
}
