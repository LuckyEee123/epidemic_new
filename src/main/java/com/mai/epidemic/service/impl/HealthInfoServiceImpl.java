package com.mai.epidemic.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.mapper.HealthInfoMapper;
import com.mai.epidemic.pojo.HealthInfo;
import com.mai.epidemic.service.HealthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (HealthInfo)表服务实现类
 *
 * @author mai
 * @since 2023-01-09 19:18:33
 */
@Service("healthInfoService")
public class HealthInfoServiceImpl extends ServiceImpl<HealthInfoMapper, HealthInfo> implements HealthInfoService {

    @Autowired
    HealthInfoMapper infoMapper;

    @Override
    public Result getUserHealthInfoById(Integer uid) {
        HealthInfo info = getById(uid);
        if (ObjectUtil.isNotNull(info)) {
            return Result.success(info);
        }
        return Result.success(null);
    }

    @Override
    public Result addHealthInfo(HealthInfo healthInfo) {
        healthInfo.setUpdateTime(DateUtil.date());
        return saveOrUpdate(healthInfo) ? Result.success("填报成功！", healthInfo) : Result.error("填报失败！");
    }
}
