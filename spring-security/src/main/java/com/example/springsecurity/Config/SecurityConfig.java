package com.example.springsecurity.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("xq")
                .password("$2a$10$isoeFEy3LvYU7wzm45dkUOKHRYw445fUw1E5RjBh.Gz5sjXFMBX0u")
                .roles("admin")
                .and()
                .withUser("kyriexu")
                .password("$2a$10$isoeFEy3LvYU7wzm45dkUOKHRYw445fUw1E5RjBh.Gz5sjXFMBX0u")
                .roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                在admin路径下的任意地址，只能admin权限的才能登陆
                .antMatchers("/admin/**").hasRole("admin")
//                user路径下的所有地址，在User和admin中任选一个都可登陆
                .antMatchers("/user/**").hasAnyRole("admin","user")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/dologin")
                .loginPage("/login")
                .usernameParameter("usr")
                .passwordParameter("pswd")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse rep, Authentication authentication) throws IOException, ServletException {
                        rep.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = rep.getWriter();
                        Map<String,Object> mp=new HashMap<>();
                        mp.put("status",200);
                        mp.put("msg",authentication.getPrincipal());
                        writer.write(new ObjectMapper().writeValueAsString(mp));
                        writer.flush();
                        writer.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse rep, AuthenticationException e) throws IOException, ServletException {
                        rep.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = rep.getWriter();
                        Map<String,Object> mp=new HashMap<>();
                        mp.put("status",401);
                        if(e instanceof LockedException){
                            mp.put("msg","帐户被锁定，登录失败");
                        }else if(e instanceof BadCredentialsException){
                            mp.put("msg","用户名或密码错误，登录失败");
                        }else {
                            mp.put("msg","登陆失败");
                        }
                        writer.write(new ObjectMapper().writeValueAsString(mp));
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse rep, Authentication authentication) throws IOException, ServletException {
                        rep.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = rep.getWriter();
                        Map<String,Object> mp=new HashMap<>();
                        mp.put("status",200);
                        mp.put("msg","注销成功");
                        writer.write(new ObjectMapper().writeValueAsString(mp));
                        writer.flush();
                        writer.close();
                    }
                })
                .and()
                .csrf().disable();
    }
}
