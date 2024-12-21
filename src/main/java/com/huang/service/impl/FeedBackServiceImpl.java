package com.huang.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.ErrorCode;
import com.huang.constant.CommonConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.mapper.FeedBackMapper;
import com.huang.mapper.PostFavourMapper;
import com.huang.mapper.PostThumbMapper;
import com.huang.mapper.ScenicMapper;
import com.huang.model.dto.feedback.FeedBackQueryRequest;
import com.huang.model.dto.post.PostQueryRequest;
import com.huang.model.dto.scenic.ScenicQueryRequest;
import com.huang.model.entity.FeedBack;
import com.huang.model.entity.Post;
import com.huang.model.entity.Scenic;
import com.huang.model.entity.User;
import com.huang.model.vo.FeedBackVO;
import com.huang.model.vo.ScenicVO;
import com.huang.model.vo.UserVO;
import com.huang.service.FeedBackService;
import com.huang.service.ScenicService;
import com.huang.service.UserService;
import com.huang.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
public class FeedBackServiceImpl extends ServiceImpl<FeedBackMapper, FeedBack> implements FeedBackService {

    @Resource
    private UserService userService;

    @Override
    public QueryWrapper<FeedBack> getQueryWrapper(FeedBackQueryRequest feedbackQueryRequest) {

        QueryWrapper<FeedBack> queryWrapper = new QueryWrapper<>();
        if (feedbackQueryRequest == null) {
            return queryWrapper;
        }
        String status = feedbackQueryRequest.getStatus();
        queryWrapper.like(StringUtils.isNotBlank(status), "status", status);
        return queryWrapper;
    }

    @Override
    public Page<FeedBackVO> getFeedBackVOPage(Page<FeedBack> feedbackPage) {
        List<FeedBack> feedBackList = feedbackPage.getRecords();
        Page<FeedBackVO> feedBackVOPage = new Page<>(feedbackPage.getCurrent(), feedbackPage.getSize(), feedbackPage.getTotal());

        // 填充信息
        List<FeedBackVO> feedBackVOS = feedBackList.stream().map(feedBack -> {
            User user = userService.getById(feedBack.getUserId());
            FeedBackVO feedBackVO = new FeedBackVO();
            BeanUtils.copyProperties(feedBack, feedBackVO);
            feedBackVO.setUser(UserVO.objToVo(user));
            return feedBackVO;
        }).collect(Collectors.toList());
        feedBackVOPage.setRecords(feedBackVOS);
        return feedBackVOPage;
    }



    @Override
    public FeedBackVO getFeedBackVOById(long id) {
        FeedBack feedBack = getById(id);
        if (feedBack == null) {
            return null;
        }
        User user = userService.getById(feedBack.getUserId());
        FeedBackVO feedBackVO = new FeedBackVO();
        feedBackVO.setId(feedBack.getId());
        feedBackVO.setTitle(feedBack.getTitle());
        feedBackVO.setContent(feedBack.getContent());
        feedBackVO.setStatus(feedBack.getStatus());
        feedBackVO.setUserId(feedBack.getUserId());
        feedBackVO.setFullName(feedBack.getFullName());
        feedBackVO.setPhoneNumber(feedBack.getPhoneNumber());
        feedBackVO.setCreateTime(feedBack.getCreateTime());
        feedBackVO.setUpdateTime(feedBack.getUpdateTime());
        feedBackVO.setUser(UserVO.objToVo(user));
        return feedBackVO;
    }


}




