package com.huang.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.huang.model.entity.Scenic;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationsVO {
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date openDateTime;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 景区封装
     */
    private ScenicVO scenicVO;

    private static final long serialVersionUID = 1L;
}
