package com.mai.epidemic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.pojo.Visitor;


/**
 * (Visitor)表服务接口
 *
 * @author mai
 * @since 2023-01-09 19:35:51
 */
public interface VisitorService extends IService<Visitor> {

    /**
     * 获取访客列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    Result getVisitorList(Integer pageNum, Integer pageSize, String name);

    /**
     * 访客登记
     * @param visitor
     * @return
     */
    Result addVisitor(Visitor visitor);
}

