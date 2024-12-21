package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.BaseResponse;
import com.huang.common.DeleteRequest;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.constant.UserConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.feedback.FeedBackAddRequest;
import com.huang.model.dto.feedback.FeedBackQueryRequest;
import com.huang.model.dto.feedback.FeedBackUpdateRequest;
import com.huang.model.entity.FeedBack;
import com.huang.model.vo.FeedBackVO;
import com.huang.service.FeedBackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 反馈管理接口
 *
 * 
 * 
 */
@RestController
@RequestMapping("/feedback")
@Slf4j
public class FeedBackController {

    @Resource
    private FeedBackService feedbackService;


    // region 增删改查

    /**
     * 创建
     *
     * @param feedbackAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addFeedBack(@RequestBody FeedBackAddRequest feedbackAddRequest) {
        if (feedbackAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        FeedBack feedback = new FeedBack();
        BeanUtils.copyProperties(feedbackAddRequest, feedback);
        feedback.setUserId(StpUtil.getLoginIdAsLong());
        feedback.setStatus("0");//未处理

        boolean result = feedbackService.save(feedback);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newFeedBackId = feedback.getId();
        return ResultUtils.success(newFeedBackId);
    }

    /**
     * 删除(仅管理员)
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteFeedBack(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 仅管理员可删除
        if (!StpUtil.hasRole(UserConstant.ADMIN_ROLE)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        long id = deleteRequest.getId();
        // 判断是否存在
        FeedBack oldFeedBack = feedbackService.getById(id);
        ThrowUtils.throwIf(oldFeedBack == null, ErrorCode.NOT_FOUND_ERROR);
        boolean b = feedbackService.removeById(id);
        return ResultUtils.success(b);
    }
//
    /**
     * 更新（仅管理员）
     *
     * @param feedbackUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateFeedBack(@RequestBody FeedBackUpdateRequest feedbackUpdateRequest) {
        System.out.println("更新"+feedbackUpdateRequest);
        if (feedbackUpdateRequest == null || feedbackUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        FeedBack feedback = new FeedBack();
        BeanUtils.copyProperties(feedbackUpdateRequest, feedback);

        long id = feedbackUpdateRequest.getId();
        // 判断是否存在
        FeedBack oldFeedBack = feedbackService.getById(id);
        ThrowUtils.throwIf(oldFeedBack == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = feedbackService.updateById(feedback);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取（VO）
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<FeedBackVO> getFeedBackVOById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        FeedBackVO feedBackVO = feedbackService.getFeedBackVOById(id);
        FeedBack feedback = feedbackService.getById(id);
        if (feedback == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(feedBackVO);
    }


    /**
     * 分页获取列表（封装类）
     *
     * @param feedbackQueryRequest
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<FeedBackVO>> listFeedBackVOByPage(@RequestBody FeedBackQueryRequest feedbackQueryRequest) {
        long current = feedbackQueryRequest.getCurrent();
        long size = feedbackQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 101, ErrorCode.PARAMS_ERROR);
        Page<FeedBack> feedbackPage = feedbackService.page(new Page<>(current, size),
                feedbackService.getQueryWrapper(feedbackQueryRequest));
        return ResultUtils.success(feedbackService.getFeedBackVOPage(feedbackPage));
    }

    @PostMapping("/list/my/page/vo")
    public BaseResponse<List<FeedBack>> listMyFeedBackVOByPage() {
        long userId = StpUtil.getLoginIdAsLong();
        QueryWrapper<FeedBack> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        List<FeedBack> feedBackList = feedbackService.list(queryWrapper);
        return ResultUtils.success(feedBackList);
    }
}
