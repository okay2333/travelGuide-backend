package com.huang.model.dto.reservationsTime;

import com.huang.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询请求
 *
 * 
 * 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ReservationsTimeQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

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
    private Date openDateTime;


    private static final long serialVersionUID = 1L;
}