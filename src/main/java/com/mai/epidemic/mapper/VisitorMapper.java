package com.mai.epidemic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mai.epidemic.pojo.Visitor;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Visitor)表数据库访问层
 *
 * @author mai
 * @since 2023-01-09 19:35:51
 */
@Mapper
public interface VisitorMapper extends BaseMapper<Visitor> {

}

