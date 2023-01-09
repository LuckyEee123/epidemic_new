package com.mai.epidemic.controller;

import com.mai.epidemic.commons.Result;
import com.mai.epidemic.pojo.HealthInfo;
import com.mai.epidemic.service.HealthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthInfo")
public class HealthController {

    @Autowired
    HealthInfoService healthInfoService;

    @GetMapping("/{uid}")
    public Result getUserHealthInfoById(@PathVariable Integer uid) {
        return healthInfoService.getUserHealthInfoById(uid);
    }

    @PostMapping("/add")
    public Result saveOrUpdateHealthInfo(@RequestBody HealthInfo healthInfo) {
        return healthInfoService.addHealthInfo(healthInfo);
    }



}
