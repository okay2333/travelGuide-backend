package com.huang.model.dto.ticket;

import com.huang.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TicketQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;


    /**
     * 景点id
     */
    private String scenicId;


    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Integer stock;

    /**
     * 开放日期
     */
    private Date openDateTime;


    private static final long serialVersionUID = 1L;
}
