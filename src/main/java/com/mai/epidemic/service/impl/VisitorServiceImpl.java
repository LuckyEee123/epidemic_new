package com.mai.epidemic.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.mapper.VisitorMapper;
import com.mai.epidemic.pojo.Visitor;
import com.mai.epidemic.pojo.vo.PageVo;
import org.springframework.stereotype.Service;
import com.mai.epidemic.service.VisitorService;

import java.util.List;

/**
 * (Visitor)表服务实现类
 *
 * @author mai
 * @since 2023-01-09 19:35:51
 */
@Service("visitorService")
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, Visitor> implements VisitorService {

    @Override
    public Result getVisitorList(Integer pageNum, Integer pageSize, String name) {
        LambdaQueryWrapper<Visitor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Visitor::getIsDelete, 0)
                .like(Visitor::getName, name);

        Page<Visitor> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());

        return Result.success(pageVo);
    }

    @Override
    public Result addVisitor(Visitor visitor) {
        visitor.setVisitDay(DateUtil.date());
        return save(visitor) ? Result.success("登记成功！", visitor): Result.error("登记失败！");
    }
}

