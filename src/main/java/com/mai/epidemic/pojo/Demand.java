package com.mai.epidemic.pojo;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Demand)表实体类
 *
 * @author mai
 * @since 2023-01-09 22:01:21
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_demand")
public class Demand  {
    // 需求id    
    @TableId
    private Integer nid;

    // 需求人身份码
    private Integer uid;
    // 需求人姓名
    private String nickname;
    // 需求级别
    private String level;
    // 需求类型
    private String type;
    // 需求
    private String needs;
    // 需求人电话
    private String phone;
    // 需求人地址
    private String address;
    // 需求进度
    private String progress;
    // 需求创建时间
    private Date createTime;
    // 需求完成时间
    private Date finishTime;
    // 0正常，1删除
    private String isDelete;

}

