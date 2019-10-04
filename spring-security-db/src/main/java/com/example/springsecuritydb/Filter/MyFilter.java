package com.example.springsecuritydb.Filter;

import com.example.springsecuritydb.Enity.Menu;
import com.example.springsecuritydb.Enity.Role;
import com.example.springsecuritydb.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {
//    路径匹配的对象
    AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Autowired
    MenuService menuService;

//    过滤器的最主要功能是分析出请求地址，根据地址分析需要哪些角色
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
//        每次请求都会调用这个方法
//        获取请求地址，Object对象实际是FilterInvocation对象，集合返回的是所需要的角色
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

//        可以考虑将Menu存到Redis里面去，因为Menu表基本不变
        List<Menu> allMenus = menuService.getAllMenus();
        for (Menu menu : allMenus) {
//            将pattern和请求地址比较，如果匹配的话，就返回对应pattern的角色
            if(antPathMatcher.match(menu.getPattern(),requestUrl)){
                List<Role> roles=menu.getRoles();
                String[] rolestr=new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    rolestr[i]=roles.get(i).getName();
                }
                return SecurityConfig.createList(rolestr);
            }
        }
//        如果匹配不上，就返回一个login对象，表示登陆之后就能访问
//        不是这个返回这个就可以访问了，还要看后续操作处理，实际上这个也就是一个标记
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

//    是否支持这种方式，默认返回false，改成true
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
