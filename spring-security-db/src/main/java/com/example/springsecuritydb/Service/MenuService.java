package com.example.springsecuritydb.Service;

import com.example.springsecuritydb.Enity.Menu;
import com.example.springsecuritydb.Mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }
}
