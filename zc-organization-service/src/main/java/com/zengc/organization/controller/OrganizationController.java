package com.zengc.organization.controller;

import com.zengc.organization.api.params.Organization;
import com.zengc.organization.api.params.OrganizationVO;
import com.zengc.organization.service.IOrganizationService;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@Api(value = "OrganizationController", tags = {""})
@RestController
@RequestMapping("/zc-organization/organization")
public class OrganizationController {

    @Autowired
    IOrganizationService organizationService;

    @PostMapping("/add")
    public Organization add(@RequestBody Organization organization) {
        return organizationService.add(organization);
    }

    @PostMapping(value="/add-with-employee", consumes = MediaType.APPLICATION_JSON_VALUE)
    @GlobalTransactional(rollbackFor = Exception.class)
    public OrganizationVO addWithEmployee(@RequestBody OrganizationVO organizationVO) {
        return organizationService.addWithEmployee(organizationVO);
    }

    @GetMapping("/all")
    public List<Organization> findAll() {
        return organizationService.findAll();
    }

    @GetMapping("/id/{id}")
    public Organization findById(@PathVariable("id") Long id) {
        return organizationService.findById(id);
    }

    @GetMapping("/with-departments/{id}")
    public OrganizationVO findByIdWithDepartments(@PathVariable("id") Long id) {
        return organizationService.findByIdWithDepartments(id);
    }

    @GetMapping("/with-departments-and-employees/{id}")
    public OrganizationVO findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
        return organizationService.findByIdWithDepartmentsAndEmployees(id);
    }


}

