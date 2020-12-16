package com.zengc.user.service;

import com.zengc.user.api.params.UserBase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengc.user.api.params.UserPermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-20
 */
public interface IUserBaseService extends IService<UserBase> {

    UserBase findById(Long id);

    List<UserPermission> findPermissionByUserId(Long userId);

}
