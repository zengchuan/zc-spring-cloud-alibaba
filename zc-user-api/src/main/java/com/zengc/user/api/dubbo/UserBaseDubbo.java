package com.zengc.user.api.dubbo;

import com.zengc.user.api.params.UserBase;
import com.zengc.user.api.params.UserPermission;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
public interface UserBaseDubbo {

    UserBase findById(Long id);

    List<UserPermission> findPermissionByUserId(Long userId);

}
