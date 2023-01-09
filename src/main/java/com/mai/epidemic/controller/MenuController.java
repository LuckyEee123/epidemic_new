package com.mai.epidemic.controller;

import com.mai.epidemic.commons.Result;
import com.mai.epidemic.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menuList")
    public Result getMenuList() {
        return menuService.getMenuList();
    }

}
