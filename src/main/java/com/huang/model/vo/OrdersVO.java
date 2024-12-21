package com.huang.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class OrdersVO implements Serializable {
    /**
     * id(订单号)
     */
    private Long id;


    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 门票id
     */
    private Long ticketId;

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

    private TicketVO ticketVO;

    private UserVO userVO;
}
