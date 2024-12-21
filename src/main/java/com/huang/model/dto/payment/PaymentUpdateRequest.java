package com.huang.model.dto.payment;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PaymentUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;
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

    private static final long serialVersionUID = 1L;
}
