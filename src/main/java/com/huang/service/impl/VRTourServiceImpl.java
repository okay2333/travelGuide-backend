package com.huang.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.ErrorCode;
import com.huang.constant.CommonConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.mapper.PostFavourMapper;
import com.huang.mapper.PostThumbMapper;
import com.huang.mapper.VRTourMapper;
import com.huang.mapper.VRTourMapper;
import com.huang.model.dto.post.PostQueryRequest;
import com.huang.model.dto.vrtour.VRTourQueryRequest;
import com.huang.model.entity.Post;
import com.huang.model.entity.VRTour;
import com.huang.model.entity.VRTour;
import com.huang.service.VRTourService;
import com.huang.service.UserService;
import com.huang.service.VRTourService;
import com.huang.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 景区管理实现
 *
 * 
 * 
 */
@Service
@Slf4j
public class VRTourServiceImpl extends ServiceImpl<VRTourMapper, VRTour> implements VRTourService {
    
    /**
     * 获取查询包装类
     *
     * @param vrTourQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<VRTour> getQueryWrapper(VRTourQueryRequest vrTourQueryRequest) {
        QueryWrapper<VRTour> queryWrapper = new QueryWrapper<>();
        if (vrTourQueryRequest == null) {
            return queryWrapper;
        }
        return queryWrapper;
    }
}




