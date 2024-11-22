package com.huang.model.dto.reservationsTime;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ReservationsTimeAddRequest implements Serializable {


    /**
     * 预约表 id
     */
    private Long reservationsId;

    /**
     * 库存数量
     */
    private Long stock;

    /**
     * 预约须知
     */
    private String instructions;

    /**
     * 开放日期
     */
    private List<Date> openDateTimes;




    private static final long serialVersionUID = 1L;
}