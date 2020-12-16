package com.zengc.department.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zengc.core.exception.ZCException;
import com.zengc.department.api.params.Department;
import com.zengc.department.api.params.DepartmentVO;
import com.zengc.department.mapper.DepartmentMapper;
import com.zengc.department.service.IDepartmentService;
import com.zengc.employee.api.dubbo.EmployeeDubbo;
import com.zengc.employee.api.feign.EmployeeFeign;
import com.zengc.employee.api.params.Employee;
import org.apache.commons.compress.utils.Lists;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Reference
    private EmployeeDubbo employeeService;
//    @Autowired
//    private EmployeeFeign employeeService;

    public Department add(Department department) {
        super.save(department);
        return department;
    }

    public DepartmentVO addWithEmployee(DepartmentVO departmentVO) {
        Department department = this.add(departmentVO.getDepartment());
        departmentVO.setDepartment(department);
        List<Employee> employees = departmentVO.getEmployees();
        if (employees != null && employees.size() > 0) {
            employees.stream().map(employee -> {
                employee.setOrganizationId(department.getOrganizationId());
                employee.setDepartmentId(department.getId());
                return employee;
            });
            List<Employee> employeeList = employeeService.addList(employees);
            departmentVO.setEmployees(employeeList);
        }
        return departmentVO;
    }

    public DepartmentVO findById(Long id) {
        DepartmentVO departmentVO = null;
        Department department = super.getById(id);
        if (department != null) {
            departmentVO = new DepartmentVO();
            departmentVO.setDepartment(department);
            try {
                List<Employee> employeeList = employeeService.findByDepartment(id);
                departmentVO.setEmployees(employeeList);
            } catch (Exception e) {
//                System.out.println("调用employeeService失败：" + e.getMessage());
                throw new ZCException("调用employeeService失败：" + e.getMessage());
            }
        }
        return departmentVO;
    }

    public List<Department> findAll() {
        return super.list();
    }

    public List<Department> findByOrganization(Long organizationId) {
        return super.list(new QueryWrapper<Department>().eq("organization_id", organizationId));
    }

    public List<DepartmentVO> findByOrganizationWithEmployees(Long organizationId) {
        List<DepartmentVO> departmentVOList = Lists.newArrayList();
        List<Department> departmentList = findByOrganization(organizationId);
        departmentList.stream().forEach(department -> {
            DepartmentVO departmentVO = new DepartmentVO();
            departmentVO.setDepartment(department);
            try {
                departmentVO.setEmployees(employeeService.findByDepartment(department.getId()));
                departmentVOList.add(departmentVO);
            } catch (Exception e) {
                throw new ZCException("调用employeeService失败：" + e.getMessage());
            }

        });
        return departmentVOList;
    }

}
