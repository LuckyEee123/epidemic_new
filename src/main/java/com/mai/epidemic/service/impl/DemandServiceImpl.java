package com.mai.epidemic.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.DeleteById;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.commons.constants.RedisConstants;
import com.mai.epidemic.commons.utils.IDUtil;
import com.mai.epidemic.commons.utils.RedisCache;
import com.mai.epidemic.mapper.DemandMapper;
import com.mai.epidemic.mapper.UserMapper;
import com.mai.epidemic.pojo.Demand;
import com.mai.epidemic.pojo.vo.PageVo;
import com.mai.epidemic.service.DemandService;
import com.mai.epidemic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * (Demand)表服务实现类
 *
 * @author mai
 * @since 2023-01-09 22:01:21
 */
@Service("demandService")
public class DemandServiceImpl extends ServiceImpl<DemandMapper, Demand> implements DemandService {

    @Autowired
    RedisCache redisCache;

    @Override
    public Result getDemandList(Integer pageNum, Integer pageSize, String nickname) {

        LambdaQueryWrapper<Demand> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Demand::getIsDelete, 0)
                .like(Demand::getNickname, nickname);

        Page<Demand> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());

        return Result.success(pageVo);
    }

    @Override
    public Result addDemand(Demand demand) {
        demand.setCreateTime(DateUtil.date());
        demand.setNid(IDUtil.FastUid());
        return save(demand) ? Result.success("添加成功！", demand): Result.error("添加失败");
    }

    @Override
    public Result deleteDemandById(Integer nid) {
        redisCache.deleteObject(RedisConstants.DEMANDS_NUM);
        return removeById(nid) ? Result.success("删除成功"): Result.error("删除失败");
    }

    @Override
    public Result getDemandsNum() {
        String demandsNum = null;
        String cache = redisCache.getCacheObject(RedisConstants.DEMANDS_NUM);
        if (StrUtil.isBlank(cache)) {
            List<Demand> list = list();
            demandsNum = String.valueOf(list.size());
            redisCache.setCacheObject(RedisConstants.DEMANDS_NUM, demandsNum, RedisConstants.CACHE_NUM_TIMEOUT, TimeUnit.SECONDS);
        } else {
            demandsNum = cache;
        }
        return Result.success("获取成功！", demandsNum);
    }

    @Override
    public Result updateDemand(Demand demand) {
        return update(demand, null) ? Result.success("编辑成功！", demand): Result.error("编辑失败！");
    }
}

