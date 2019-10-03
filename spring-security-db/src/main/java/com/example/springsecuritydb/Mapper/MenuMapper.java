package com.example.springsecuritydb.Mapper;

import com.example.springsecuritydb.Enity.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Select("select * from menu")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "pattern",column = "pattern"),
            @Result(property = "roles",column = "id",many = @Many(select ="com.example.springsecuritydb.Mapper.RoleMapper.getById"))
    })
    List<Menu> getAllMenus();
}
