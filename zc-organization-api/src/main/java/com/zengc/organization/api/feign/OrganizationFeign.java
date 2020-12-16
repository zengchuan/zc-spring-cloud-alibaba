package com.zengc.organization.api.feign;

import com.zengc.employee.api.params.Employee;
import com.zengc.organization.api.params.OrganizationVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
@FeignClient(name = "zc-organization-service")
public interface OrganizationFeign {

    @GetMapping(value="/zc-organization-service/zc-organization/organization/with-departments-and-employees/{id}", produces = "application/json;charset=UTF-8")
    OrganizationVO findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) ;


}
