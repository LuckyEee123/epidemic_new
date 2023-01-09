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
    public Result getUsers(Integer pageNum, Integer pageSize, String nickname) {
        return userService.getUsersList(pageNum, pageSize, nickname);
    }

    @GetMapping("/usersNum")
    public Result getUsersNum() {
        return userService.getUsersNum();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:users:add')")
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/delete/{uid}")
    @PreAuthorize("hasAuthority('sys:users:delete')")
    public Result deleteUserById(@PathVariable Integer uid) {
        return userService.deleteUserById(uid);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:users:update')")
    public Result updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

}
