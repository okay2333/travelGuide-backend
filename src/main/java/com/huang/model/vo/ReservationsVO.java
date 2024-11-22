package com.huang.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ReservationsVO {
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
