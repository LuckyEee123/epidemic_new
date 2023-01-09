package com.mai.epidemic.controller;

import com.mai.epidemic.commons.Result;
import com.mai.epidemic.pojo.Visitor;
import com.mai.epidemic.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    VisitorService visitorService;

    @GetMapping("/getVisitorList")
    public Result getVisitorList(Integer pageNum, Integer pageSize, String name) {
        return visitorService.getVisitorList(pageNum, pageSize, name);
    }

    @PostMapping("/add")
    public Result addVisitor(@RequestBody Visitor visitor) {
        return visitorService.addVisitor(visitor);
    }

}
