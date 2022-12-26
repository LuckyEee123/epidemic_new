package com.mai.epidemic.controller;

import com.mai.epidemic.commons.Result;
import com.mai.epidemic.pojo.User;
import com.mai.epidemic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/userList")
    @PreAuthorize("hasAuthority('sys:test:list')")
    public Result getUsers() {
        return Result.success(userService.list());
    }

    @PostMapping("/add")
    public Result addUser(@RequestBody User user) {
        userService.addUser(user);
        return Result.success();
    }

}
