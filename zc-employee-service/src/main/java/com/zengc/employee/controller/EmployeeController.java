package com.zengc.employee.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zengc.employee.api.params.Employee;
import com.zengc.employee.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
@Api(value = "EmployeeController", tags = {"员工API"})
@RestController
@RequestMapping("/zc-employee/employee")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    IEmployeeService employeeService;

    @ApiOperation("添加员工数据")
    @PostMapping("/add")
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        return employeeService.add(employee);
    }

    @PostMapping(value="/add-list", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public List<Employee> addList(@RequestBody List<Employee> employeeList){
        return employeeService.addList(employeeList);
    }

    @ApiOperation("根据员工id查找员工数据")
    @ApiImplicitParam(name = "id",value = "员工id", required = true, paramType = "path")
    @GetMapping("/id/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        return employeeService.findById(id);
    }

    @GetMapping("/all")
    public List<Employee> findAll() {
        LOGGER.info("Employee find");
        List<Employee> all = employeeService.findAll();
        return all;
    }

    @GetMapping("/department/{departmentId}")
    @SentinelResource(value = "zc-employee-service-findByDepartment")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return employeeService.findByDepartment(departmentId);
    }

    @GetMapping("/organization/{organizationId}")
    public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Employee find: organizationId={}", organizationId);
        return employeeService.findByOrganization(organizationId);
    }

}

