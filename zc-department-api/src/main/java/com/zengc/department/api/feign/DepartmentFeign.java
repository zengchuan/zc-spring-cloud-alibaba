package com.zengc.department.api.feign;

import com.zengc.department.api.params.Department;
import com.zengc.department.api.params.DepartmentVO;
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
@FeignClient(name = "zc-department-service")
public interface DepartmentFeign {

    @PostMapping(value = "/zc-department-service/zc-department/department/add-with-employee", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    DepartmentVO addWithEmployee(@RequestBody DepartmentVO departmentVO);

    @GetMapping(value = "/zc-department-service/zc-department/department/organization/{organizationId}", produces = "application/json;charset=UTF-8")
    List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId);

    @GetMapping(value="/zc-department-service/zc-department/department/organization/{organizationId}/with-employees", produces = "application/json;charset=UTF-8")
    List<DepartmentVO> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId);

}
