package com.huang.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约时间表
 * @TableName reservations_time
 */
@TableName(value ="reservations_time")
@Data
public class ReservationsTime implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 预约表 id
     */
    private Long reservationsId;

    /**
     * 库存数量
     */
    private Long stock;

    /**
     * 预约须知
     */
    private String instructions;

    /**
     * 开放日期
     */
    private Date openDateTime;

    /**
     * 时间段（json 数组）
     */
    private String timeSlot;

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