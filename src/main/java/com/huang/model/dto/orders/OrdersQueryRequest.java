package com.huang.model.dto.orders;

import lombok.Data;

@Data
public class OrdersQueryRequest {
    private Long userId;            // 用户 ID
    private Long current;           // 当前页
    private Long pageSize;          // 每页大小
}