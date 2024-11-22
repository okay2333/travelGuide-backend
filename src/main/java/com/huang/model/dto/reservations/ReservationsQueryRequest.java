package com.huang.model.dto.reservations;

import com.huang.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 查询请求
 *
 * 
 * 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ReservationsQueryRequest extends PageRequest implements Serializable {

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