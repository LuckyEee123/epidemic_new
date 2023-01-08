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
    @PreAuthorize("hasAuthority('sys:users:list')")
    public Result getUsers() {
        return Result.success(userService.list());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:users:add')")
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/delete/{id}")
    public Result deleteUserById(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }

}
