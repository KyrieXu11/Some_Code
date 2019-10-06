package com.example.oauth2.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("KyrieXu")
                .password("$2a$10$isoeFEy3LvYU7wzm45dkUOKHRYw445fUw1E5RjBh.Gz5sjXFMBX0u")
                .roles("admin")
                .and()
                .withUser("Xu")
                .password("$2a$10$isoeFEy3LvYU7wzm45dkUOKHRYw445fUw1E5RjBh.Gz5sjXFMBX0u")
                .roles("user");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/oauth/**")
////                authorizeRequests请求授权
//                .authorizeRequests()
//                .antMatchers("/oauth/**")
//                .permitAll()
//                .and().csrf().disable();
//    }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/oauth/**")
                    .authorizeRequests()
                    .antMatchers("/oauth/**").permitAll()
                    .and().csrf().disable();
        }
}
