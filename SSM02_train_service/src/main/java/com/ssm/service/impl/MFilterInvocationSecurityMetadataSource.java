package com.ssm.service.impl;

import com.ssm.dao.IPermissionDao;
import com.ssm.dao.IRoleDao;
import com.ssm.dao.IUserDao;
import com.ssm.domain.Permission;
import com.ssm.domain.Role;
import com.ssm.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.io.File;
import java.util.*;

//获取请求的url和数据库中的对比
@Service("MFilterInvocationSecurityMetadataSource")
public class MFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IPermissionDao permissionDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IUserDao userDao;

    private PathMatcher antPathMatcher = new AntPathMatcher();

    //  key  是url      value是角色     一个路径可以有多个角色所以是集合
    private Map<String, List<ConfigAttribute>> resources;
    //此方法是初始化   resources   将数据库里的角色 和角色对应的路径查询出来 放入resources中
    private void loadAuthorityResources() {
        resources = new HashMap<>();
        List<Permission> all = permissionDao.findAll();//此方法是查询所有 url 和相关数据
        for (Permission permission : all) {
            String permissionId = permission.getId();//通过ID来获取角色
            List<Role> roleB = roleDao.findRoleBypermissionId(permissionId);
            List<ConfigAttribute> authorityList = new ArrayList<>();
            //将url对应的所有角色存入到 authorityList中
            for (Role role : roleB) {
                authorityList.add(new SecurityConfig("ROLE_"+role.getRoleName()));
            }
            //将 URL  和  authorityList(角色)   添加到 resources中
            resources.put(permission.getUrl(), authorityList);
        }

    }

    //此方法就是  拦截路径的
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        loadAuthorityResources();  //调用初始化方法
        String url = ((FilterInvocation) object).getRequestUrl();//获取用户请求的url

        if (url.equals("/user/register")){
            return null;
        }

        //遍历 resources
        for(Map.Entry<String, List<ConfigAttribute>> entry:resources.entrySet()){

            //查询用户是否被启用
            SecurityContext context = SecurityContextHolder.getContext();//从上下文中获得当前登录的用户
            System.out.println(context.getAuthentication().getPrincipal());
            if ("anonymousUser".equals(context.getAuthentication().getPrincipal())){
                return entry.getValue();
            }
            User user =(User) context.getAuthentication().getPrincipal();
            UserInfo userInfo = userDao.findByUsername(user.getUsername());
            if (userInfo.getStatus()==0){
                System.out.println("用户"+user.getUsername()+"被禁");
                return entry.getValue();
            }else {
                System.out.println("用户"+user.getUsername()+"没被禁");
            }

            //左边匹配右边  左边数据库  右边url
            // ** 代表能匹配 路径参数   如果不加** 那么就是绝对匹配不能差一点
            String key_url =entry.getKey(); //获取url
            //判断是否加**
            if (url.contains("?")&&!url.endsWith(File.separator)&&!(key_url.contains("*")||key_url.contains("**"))){
                key_url+="**";
            }
            //判断数据库中的URL 和请求的URL 是否匹配
            if (antPathMatcher.match(key_url,url)) {
                if (entry.getValue().size()==0){
                    break;
                }
                //将角色传递给 MyAccessDecisionManager  由他处理
                return entry.getValue();
            }

        }
        //如果没有匹配成功，就说明该请求没有加权限，
        return null;  //不拦截
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
