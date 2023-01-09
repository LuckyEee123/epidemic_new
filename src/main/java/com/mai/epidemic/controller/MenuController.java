package com.mai.epidemic.controller;

import com.mai.epidemic.commons.Result;
import com.mai.epidemic.pojo.Menu;
import com.mai.epidemic.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menuList")
    public Result getMenuList() {
        return menuService.getMenuList();
    }

    @PostMapping("/addPerm")
    public Result addPerm(@RequestBody Menu menu) {
        return menuService.addPerm(menu);
    }

    @PostMapping("/updatePerm")
    public Result updatePermById(@RequestBody Menu menu) {
        return menuService.updatePermById(menu);
    }

    @PostMapping("/delete/{id}")
    public Result deletePermById(@PathVariable Integer id) {
        return menuService.deletePermById(id);
    }

}
