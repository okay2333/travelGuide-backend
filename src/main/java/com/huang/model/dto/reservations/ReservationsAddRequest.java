package com.huang.model.dto.reservations;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ReservationsAddRequest implements Serializable {

    /**
     * 景区id
     */
    private Long scenicId;

    /**
     * 库存数量
     */
    private Long stock;

    /**
     * 开放时间
     */
    private Date openDateTime;


    private static final long serialVersionUID = 1L;

}