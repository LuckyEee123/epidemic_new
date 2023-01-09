package com.mai.epidemic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mai.epidemic.pojo.Demand;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Demand)表数据库访问层
 *
 * @author mai
 * @since 2023-01-09 22:01:21
 */
@Mapper
public interface DemandMapper extends BaseMapper<Demand> {

}

