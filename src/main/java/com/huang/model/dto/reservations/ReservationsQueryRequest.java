package com.huang.model.dto.reservations;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.huang.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
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

    /**
     * 搜索词
     */
    private String searchText;


    private static final long serialVersionUID = 1L;
}