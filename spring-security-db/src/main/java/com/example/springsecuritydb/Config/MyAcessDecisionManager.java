package com.example.springsecuritydb.Config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

//  已经得到了需要哪些角色，将这些角色和已有角色对比
@Component
public class MyAcessDecisionManager implements AccessDecisionManager {
//    第一个参数存放着的是登陆用户的信息，第二个参数就是MyFilter中的Object参数
//    第三个参数是访问路径需要的权限
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute attribute : collection) {
            if("ROLE_LOGIN".equals(attribute.getAttribute())){
                if(authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("非法请求");
                }else {
                    return;
                }
            }
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if(authority.getAuthority().equals(attribute.getAttribute())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("非法请求");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
