package com.huang.model.dto.ticket;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TicketUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 景点id
     */
    private Long scenicId;


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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
