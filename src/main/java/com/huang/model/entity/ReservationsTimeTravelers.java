package com.huang.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约用户信息表
 * @TableName reservations_time_travelers
 */
@TableName(value ="reservations_time_travelers")
@Data
public class ReservationsTimeTravelers implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 预约时间表 id
     */
    private Long reservationsId;

    /**
     * 预约时间表 id
     */
    private Long reservationsTimeId;

    /**
     * 姓名
     */
    private String fullName;

    /**
     * 身份证
     */
    private String idNumber;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}