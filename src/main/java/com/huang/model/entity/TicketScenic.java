package com.huang.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 门票景点表
 * @TableName ticketscenic
 */
@TableName(value ="ticketscenic")
@Data
public class TicketScenic implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 轮播图（JSON数组或逗号分隔的字符串）
     */
    private String carouselImages;

    /**
     * 景点名称
     */
    private String scenicName;

    /**
     * 标签（逗号分隔）
     */
    private String tags;

    /**
     * 开放时间
     */
    private String openingHours;

    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 地址
     */
    private String address;

    /**
     * 详情
     */
    private String details;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Integer stock;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}