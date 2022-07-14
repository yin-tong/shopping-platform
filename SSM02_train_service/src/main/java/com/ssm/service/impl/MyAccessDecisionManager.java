package com.ssm.service.impl;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

//获取返回来的角色进行判断
@Service("MyAccessDecisionManager")
public class MyAccessDecisionManager implements AccessDecisionManager {
    /*
     * 该方法决定该权限是否有权限访问该资源，其实object就是一个资源的地址，authentication是当前用户对应权限角色
     * ，如果没登陆就为游客匿名用户ROLE_ANONYMOUS，登陆了就是该用户对应的权限
     */
    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) {
            return;
        }
        System.out.println(object.toString()); //  URL.
        //所请求的资源拥有的角色(一个URL对 多个角色)
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            //访问所请求资源所需要的权限
            String needPermission = configAttribute.getAttribute();
            System.out.println("访问" + object.toString() + "路径" + "需要的权限是：" + needPermission);
            //用户所拥有的权限authentication
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority ga : authorities) {
                System.out.println("用户所拥有的权限" + ga.getAuthority());
                if (needPermission.equals(ga.getAuthority())) {
                    System.out.println("有权限了");
                    return;  //放行
                }
            }
        }
        //没有权限    这个异常会触发403
        throw new AccessDeniedException(" 没有权限访问！");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
