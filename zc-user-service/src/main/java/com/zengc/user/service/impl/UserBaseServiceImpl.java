package com.zengc.user.service.impl;

import com.zengc.user.api.params.UserBase;
import com.zengc.user.api.params.UserPermission;
import com.zengc.user.mapper.UserBaseMapper;
import com.zengc.user.service.IUserBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-20
 */
@Service
public class UserBaseServiceImpl extends ServiceImpl<UserBaseMapper, UserBase> implements IUserBaseService {

    public UserBase findById(Long id){
        return super.getById(id);
    }

    public List<UserPermission> findPermissionByUserId(Long userId){
        return baseMapper.findPermissionByUserId(userId);
    }
}
