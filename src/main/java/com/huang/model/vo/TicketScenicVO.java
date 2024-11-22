package com.huang.model.vo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.huang.model.entity.Post;
import com.huang.model.entity.TicketScenic;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class TicketScenicVO implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;


    /**
     * 图片列表 json
     */
    private List<String> coverList;

    /**
     * 景点名称
     */
    private String scenicName;

    /**
     * 标签列表
     */
    private List<String> tagList;

    /**
     * 开放时间
     */
    private String openingHours;

    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 地址
     */
    private String address;

    /**
     * 详情
     */
    private String details;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Integer stock;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * 包装类转对象
     *
     * @param ticketScenicVO
     * @return
     */
    public static TicketScenic voToObj(TicketScenicVO ticketScenicVO) {
        if (ticketScenicVO == null) {
            return null;
        }
        TicketScenic ticketScenic = new TicketScenic();
        BeanUtils.copyProperties(ticketScenicVO, ticketScenic);
        List<String> tagList = ticketScenicVO.getTagList();
        ticketScenic.setTags(JSONUtil.toJsonStr(tagList));
        return ticketScenic;
    }

    /**
     * 对象转包装类
     *
     * @param ticketScenic
     * @return
     */
    public static TicketScenicVO objToVo(TicketScenic ticketScenic) {
        if (ticketScenic == null) {
            return null;
        }
        TicketScenicVO ticketScenicVO = new TicketScenicVO();
        BeanUtils.copyProperties(ticketScenic, ticketScenicVO);
        ticketScenicVO.setCoverList(JSONUtil.toList(ticketScenic.getCarouselImages(), String.class));
        ticketScenicVO.setTagList(JSONUtil.toList(ticketScenic.getTags(), String.class));
        return ticketScenicVO;
    }
}
