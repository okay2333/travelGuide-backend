package com.huang.model.vo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.huang.model.entity.Post;
import com.huang.model.entity.Scenic;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 景区视图
 *

 */
@Data
public class ScenicVO implements Serializable {

    /**
     * 景点id
     */
    private Long id;


    /**
     * 景点名称
     */
    private String scenicName;


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
     * 图片列表 json
     */
    private List<String> carouselImagesList;

    /**
     * 标签列表
     */
    private List<String> tagList;




    /**
     * 包装类转对象
     *
     * @param scenicVO
     * @return
     */
    public static Scenic voToObj(ScenicVO scenicVO) {
        if (scenicVO == null) {
            return null;
        }
        Scenic scenic = new Scenic();
        BeanUtils.copyProperties(scenicVO, scenic);
        List<String> tagList = scenicVO.getTagList();
        scenic.setTags(JSONUtil.toJsonStr(tagList));
        return scenic;
    }

    /**
     * 对象转包装类
     *
     * @param scenic
     * @return
     */
    public static ScenicVO objToVo(Scenic scenic) {
        if (scenic == null) {
            return null;
        }
        ScenicVO scenicVO = new ScenicVO();
        BeanUtils.copyProperties(scenic, scenicVO);
        scenicVO.setCarouselImagesList(JSONUtil.toList(scenic.getCarouselImages(), String.class));
        scenicVO.setTagList(JSONUtil.toList(scenic.getTags(), String.class));
        return scenicVO;
    }
}
