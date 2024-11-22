package com.huang.model.dto.reservations;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ReservationsAddRequest implements Serializable {


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