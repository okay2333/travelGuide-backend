package com.huang.model.dto.orders;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrdersEditRequest {
    private Long id;                // 订单 ID
    private String orderId;         // 订单号
    private Long ticketScenicId;    // 门票景点 ID
    private Integer orderStatus;      // 订单状态
    private Integer quantity;        // 购买数量
    private BigDecimal totalPrice;   // 总价格
}
