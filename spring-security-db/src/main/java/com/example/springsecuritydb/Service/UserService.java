package com.example.springsecuritydb.Service;

import com.example.springsecuritydb.Enity.User;
import com.example.springsecuritydb.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.getUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setRoles(userMapper.getRoleByID(user.getId()));
        return user;
    }
}
