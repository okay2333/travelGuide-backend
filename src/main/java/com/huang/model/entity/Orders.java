package com.huang.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 订单表
 * @TableName orders
 */
@TableName(value ="orders")
@Data
public class Orders implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 门票景点id
     */
    private Long ticketScenicId;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 购买时间
     */
    private Date purchaseDate;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 总价格
     */
    private BigDecimal totalPrice;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}