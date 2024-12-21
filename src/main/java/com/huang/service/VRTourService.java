package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.post.PostQueryRequest;
import com.huang.model.dto.scenic.ScenicQueryRequest;
import com.huang.model.dto.vrtour.VRTourQueryRequest;
import com.huang.model.entity.Post;
import com.huang.model.entity.Scenic;
import com.huang.model.entity.VRTour;
import com.huang.model.vo.ScenicVO;

/**
 * VR管理
 *
 * 
 * 
 */
public interface VRTourService extends IService<VRTour> {



    /**
     * 获取查询条件
     *
     * @param vrTourQueryRequest
     * @return
     */
    QueryWrapper<VRTour> getQueryWrapper(VRTourQueryRequest vrTourQueryRequest);










}
