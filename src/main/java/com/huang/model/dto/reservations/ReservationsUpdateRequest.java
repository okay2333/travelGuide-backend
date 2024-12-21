package com.huang.model.dto.reservations;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReservationsUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 景区表 id
     */
    private Long scenicId;

    /**
     * 库存数量
     */
    private Long stock;


    /**
     * 开放日期
     */
    private Date openDateTime;



    private static final long serialVersionUID = 1L;
}