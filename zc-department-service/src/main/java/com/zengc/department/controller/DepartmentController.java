package com.zengc.department.controller;

import com.zengc.department.api.params.Department;
import com.zengc.department.api.params.DepartmentVO;
import com.zengc.department.service.IDepartmentService;
import io.swagger.annotations.Api;
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
@Api(value = "DepartmentController", tags = {""})
@RestController
@RequestMapping("/zc-department/department")
public class DepartmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private IDepartmentService departmentService;

    @PostMapping("/add")
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department add: {}", department);
        return departmentService.add(department);
    }

    @PostMapping("/add-with-employee")
    public DepartmentVO addWithEmployee(@RequestBody DepartmentVO departmentVO) {
        return departmentService.addWithEmployee(departmentVO);
    }

    @GetMapping("/id/{id}")
    public DepartmentVO findById(@PathVariable("id") Long id) {
        LOGGER.info("Department find: id={}", id);
        return departmentService.findById(id);
    }

    @GetMapping("/all")
    public List<Department> findAll() {
        LOGGER.info("Department find");
        return departmentService.findAll();
    }

    @GetMapping("/organization/{organizationId}")
    public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Department find: organizationId={}", organizationId);
        return departmentService.findByOrganization(organizationId);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public List<DepartmentVO> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
        return departmentService.findByOrganizationWithEmployees(organizationId);
    }
}

