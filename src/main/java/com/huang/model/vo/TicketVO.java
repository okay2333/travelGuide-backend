package com.huang.model.vo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.huang.model.entity.Ticket;
import com.huang.model.entity.Ticket;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class TicketVO implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;


    /**
     * 景点id
     */
    private Long scenicId;


    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Integer stock;

    /**
     * 开放日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date openDateTime;

    /**
     * 景区封装
     */
    private ScenicVO scenicVO;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 包装类转对象
     *
     * @param ticketVO
     * @return
     */
    public static Ticket voToObj(TicketVO ticketVO) {
        if (ticketVO == null) {
            return null;
        }
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketVO, ticket);
        return ticket;
    }

    /**
     * 对象转包装类
     *
     * @param ticket
     * @return
     */
    public static TicketVO objToVo(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        TicketVO ticketVO = new TicketVO();
        BeanUtils.copyProperties(ticket, ticketVO);
        return ticketVO;
    }


}
