package com.zengc.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zengc.user.api.params.UserBase;
import com.zengc.user.api.params.UserPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-20
 */
public interface UserBaseMapper extends BaseMapper<UserBase> {

    List<UserPermission> findPermissionByUserId(@Param("userId") Long userId);

}
