/*
 *  Copyright 2019 https://github.com/romeoblog/spring-cloud.git Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.zengc.auth.service.impl;

import com.zengc.user.api.dubbo.UserBaseDubbo;
import com.zengc.user.api.params.UserBase;
import com.zengc.user.api.params.UserPermission;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义认证授权实现类
 *
 * @author zeengchuan
 * @date 2020-06-07
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Reference
    private UserBaseDubbo userBaseService;
//    @Autowired
//    private UserBaseFeign userBaseService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Long id = Long.valueOf(userName);
        UserBase user = userBaseService.findById(id);

        if (user == null) {
            throw new UsernameNotFoundException("用户名[" + userName + "]不存在！");
        }

        List<UserPermission> userPermissionList = userBaseService.findPermissionByUserId(id);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if (userPermissionList != null && userPermissionList.size() > 0) {
            for (UserPermission permission : userPermissionList) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getNameE());
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return new User(String.valueOf(user.getId()), user.getPassword(), grantedAuthorities);
    }
}
