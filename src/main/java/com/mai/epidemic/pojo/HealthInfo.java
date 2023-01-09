package com.mai.epidemic.pojo;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (HealthInfo)表实体类
 *
 * @author mai
 * @since 2023-01-09 19:18:32
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_health_info")
public class HealthInfo  {
    // 身份码    
    @TableId
    private Integer uid;

    // 健康信息
    private String bodyInfo;
    // 旅居史
    private String travelInfo;
    // 核酸结果
    private String result;
    // 信息更新时间
    private Date updateTime;
    // 0正常，1删除
    private String isDelete;

}

