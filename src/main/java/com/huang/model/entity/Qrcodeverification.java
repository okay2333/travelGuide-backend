package com.huang.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 二维码核销记录表
 * @TableName qrcodeverification
 */
@TableName(value ="qrcodeverification")
@Data
public class Qrcodeverification implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 核销二维码
     */
    private String qrCode;

    /**
     * 是否已核销
     */
    private Integer isVerified;

    /**
     * 核销时间
     */
    private Date verificationDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}