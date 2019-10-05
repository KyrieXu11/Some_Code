package com.example.springsecuritydb.Mapper;

import com.example.springsecuritydb.Enity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "nameZh",column = "nameZh")
    })
    @Select("select * from role where id= #{id}")
    List<Role> getById(@Param("id") Integer id);
}
