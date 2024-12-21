package com.huang.model.dto.orders;

import com.huang.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersQueryRequest  extends PageRequest implements Serializable {
    private Long userId;            // 用户 ID
    private Long scenicId;          // 景区 ID
    private Integer status;         // 订单状态

}