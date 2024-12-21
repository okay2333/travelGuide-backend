package com.huang.model.dto.scenic;

import com.huang.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
public class ScenicQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;
    /**
     * 轮播图（JSON数组或逗号分隔的字符串）
     */
    private List<String> carouselImages;

    /**
     * 景点名称
     */
    private String scenicName;

    /**
     * 标签（逗号分隔）
     */
    private  List<String> tags;

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
     * 1免费预约0购买门票
     */
    private Integer type;

    /**
     * 搜索词
     */
    private String searchText;

    private static final long serialVersionUID = 1L;
}
