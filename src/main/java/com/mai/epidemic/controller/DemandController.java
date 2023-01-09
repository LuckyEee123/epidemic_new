package com.mai.epidemic.controller;

import com.mai.epidemic.commons.Result;
import com.mai.epidemic.pojo.Demand;
import com.mai.epidemic.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demand")
public class DemandController {

    @Autowired
    DemandService demandService;

    @GetMapping("/getDemandList")
    public Result getDemandList(Integer pageNum, Integer pageSize, String nickname) {
        return demandService.getDemandList(pageNum, pageSize, nickname);
    }

    @GetMapping("/getDemandsNum")
    public Result getDemandsNum() {
        return demandService.getDemandsNum();
    }

    @PostMapping("/add")
    public Result addDemand(@RequestBody Demand demand) {
        return demandService.addDemand(demand);
    }

    @PostMapping("/delete/{nid}")
    public Result deleteDemand(@PathVariable Integer nid) {
        return demandService.deleteDemandById(nid);
    }

    public Result updateDemand(@RequestBody Demand demand) {
        return demandService.updateDemand(demand);
    }

}
