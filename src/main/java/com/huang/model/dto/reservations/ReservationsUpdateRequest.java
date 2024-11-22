package com.huang.model.dto.reservations;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReservationsUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 景区名称
     */
    private String scenicName;

    /**
     * 预约须知
     */
    private String instructions;


    private static final long serialVersionUID = 1L;
}