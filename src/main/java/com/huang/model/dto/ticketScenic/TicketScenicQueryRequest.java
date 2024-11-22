package com.huang.model.dto.ticketScenic;

import com.huang.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class TicketScenicQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;


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
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Integer stock;


    private static final long serialVersionUID = 1L;
}
