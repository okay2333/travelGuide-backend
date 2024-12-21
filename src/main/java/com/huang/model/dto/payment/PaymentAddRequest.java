package com.huang.model.dto.payment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaymentAddRequest {

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 支付状态
     */
    private Integer paymentStatus;

    /**
     * 支付金额
     */
    private BigDecimal paymentAmount;

}