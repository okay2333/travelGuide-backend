package com.huang.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huang.model.dto.reservationsTime.Traveler;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class ReservationsTravelersVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 预约表 id
     */
    private Long reservationsId;

    /**
     * 集合
     */
    private List<Traveler> travelerList;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private ReservationsVO reservationsVO;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}