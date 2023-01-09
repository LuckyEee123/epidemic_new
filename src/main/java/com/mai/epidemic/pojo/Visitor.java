package com.mai.epidemic.pojo;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Visitor)表实体类
 *
 * @author mai
 * @since 2023-01-09 19:35:51
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_visitor")
public class Visitor  {
    // 访客id    
    @TableId
    private Integer id;

    // 访客姓名
    private String name;
    // 访客电话
    private String phone;
    // 核酸结果
    private String result;
    // 来访原因
    private String reason;
    // 来访日期
    private Date visitDay;
    // 来访问谁
    private String visitWho;
    // 0正常，1删除
    private String isDelete;

}

