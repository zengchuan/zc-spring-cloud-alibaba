package com.zengc.user.api.feign;

import com.zengc.user.api.params.UserBase;
import com.zengc.user.api.params.UserPermission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
@FeignClient(name = "zc-user-service")
public interface UserBaseFeign {

    @GetMapping(value = "/zc-user-service/zc-user/userBase/id/{id}", produces = "application/json;charset=UTF-8")
    UserBase findById(@PathVariable("id") Long id);

    @GetMapping(value = "/zc-user-service/zc-user/userBase/user-permission/{id}", produces = "application/json;charset=UTF-8")
    List<UserPermission> findPermissionByUserId(@PathVariable("id") Long id);

}
