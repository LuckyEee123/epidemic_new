package com.mai.epidemic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.pojo.Demand;


/**
 * (Demand)表服务接口
 *
 * @author mai
 * @since 2023-01-09 22:01:21
 */
public interface DemandService extends IService<Demand> {

    /**
     * 获取需求列表
     * @return
     */
    Result getDemandList(Integer pageNum, Integer pageSize, String nickname);

    /**
     * 添加需求
     * @param demand
     * @return
     */
    Result addDemand(Demand demand);

    /**
     * 删除需求
     * @param nid
     * @return
     */
    Result deleteDemandById(Integer nid);
}

