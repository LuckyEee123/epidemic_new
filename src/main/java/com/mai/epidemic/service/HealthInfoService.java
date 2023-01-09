package com.mai.epidemic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.pojo.HealthInfo;


/**
 * (HealthInfo)表服务接口
 *
 * @author mai
 * @since 2023-01-09 19:18:33
 */
public interface HealthInfoService extends IService<HealthInfo> {

    /**
     * 查询用户的健康信息
     * @param uid
     * @return
     */
    Result getUserHealthInfoById(Integer uid);

    /**
     * 填报健康信息
     * @param healthInfo
     * @return
     */
    Result addHealthInfo(HealthInfo healthInfo);
}

