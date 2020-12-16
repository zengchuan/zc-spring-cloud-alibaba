package com.zengc.user.controller;

import com.zengc.user.api.params.UserBase;
import com.zengc.user.api.params.UserPermission;
import com.zengc.user.service.IUserBaseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-20
 */
@Api(value = "UserBaseController", tags = {""})
@RestController
@RequestMapping("/zc-user/userBase")
public class UserBaseController {

    @Autowired
    IUserBaseService userBaseService;

    @GetMapping("/id/{id}")
    public UserBase findById(@PathVariable("id") Long id) {
        return userBaseService.findById(id);
    }

    @PutMapping("/id/{id}")
    public UserBase updateById(@PathVariable("id") Long id) {
        return userBaseService.findById(id);
    }

    @GetMapping("/user-permission/{id}")
    public List<UserPermission> findPermissionByUserId(@PathVariable("id") Long id) {
        List<UserPermission> userPermissionList = userBaseService.findPermissionByUserId(id);
        return userPermissionList;
    }
}

