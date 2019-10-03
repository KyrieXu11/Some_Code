package com.example.springsecuritydb.Mapper;

import com.example.springsecuritydb.Enity.Role;
import com.example.springsecuritydb.Enity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User getUserByUsername(@Param(value = "username") String username);

    @Select("select * from role where id in (select rid from user_role where uid=#{id})")
    List<Role> getRoleByID(@Param(value = "id") Integer id);
}
