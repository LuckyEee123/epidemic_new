package com.mai.epidemic.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.DeleteById;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.commons.utils.IDUtil;
import com.mai.epidemic.mapper.DemandMapper;
import com.mai.epidemic.mapper.UserMapper;
import com.mai.epidemic.pojo.Demand;
import com.mai.epidemic.pojo.vo.PageVo;
import com.mai.epidemic.service.DemandService;
import com.mai.epidemic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Demand)表服务实现类
 *
 * @author mai
 * @since 2023-01-09 22:01:21
 */
@Service("demandService")
public class DemandServiceImpl extends ServiceImpl<DemandMapper, Demand> implements DemandService {

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
        return removeById(nid) ? Result.success("删除成功"): Result.error("删除失败");
    }
}

