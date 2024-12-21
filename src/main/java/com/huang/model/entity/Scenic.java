package com.huang.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 景点表
 * @TableName scenic
 */
@TableName(value ="scenic")
@Data
public class Scenic implements Serializable {
    /**
     * 景点id
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
     * 1免费预约0购买门票
     */
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}