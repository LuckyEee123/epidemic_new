package com.mai.epidemic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mai.epidemic.pojo.HealthInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * (HealthInfo)表数据库访问层
 *
 * @author mai
 * @since 2023-01-09 19:18:31
 */
@Mapper
public interface HealthInfoMapper extends BaseMapper<HealthInfo> {

}

