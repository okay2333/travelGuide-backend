package com.huang.model.dto.orders;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrdersAddRequest {
    private Long ticketId;    // 门票景点 ID
    private Integer quantity;        // 购买数量
    private BigDecimal totalPrice;   // 总价格
}