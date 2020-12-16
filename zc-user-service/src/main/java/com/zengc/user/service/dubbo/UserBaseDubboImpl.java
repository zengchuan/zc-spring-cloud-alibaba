package com.zengc.user.service.dubbo;

import com.zengc.user.api.dubbo.UserBaseDubbo;
import com.zengc.user.api.params.UserBase;
import com.zengc.user.api.params.UserPermission;
import com.zengc.user.service.IUserBaseService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
public class UserBaseDubboImpl implements UserBaseDubbo {

    @Autowired
    IUserBaseService userBaseService;

    public UserBase findById(Long id) {
        return userBaseService.getById(id);
    }

    public List<UserPermission> findPermissionByUserId(Long userId) {
        return userBaseService.findPermissionByUserId(userId);
    }
}
