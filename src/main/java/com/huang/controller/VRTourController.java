package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.BaseResponse;
import com.huang.common.DeleteRequest;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.constant.UserConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;

import com.huang.model.dto.vrtour.VRTourAddRequest;
import com.huang.model.dto.vrtour.VRTourQueryRequest;
import com.huang.model.dto.vrtour.VRTourUpdateRequest;
import com.huang.model.entity.VRTour;
import com.huang.service.VRTourService;
import com.huang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * VR管理接口
 *
 * 
 * 
 */
@RestController
@RequestMapping("/vrtour")
@Slf4j
public class VRTourController {

    @Resource
    private VRTourService vrTourService;
    

    // region 增删改查

    /**
     * 创建
     *
     * @param vrTourAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addVRTour(@RequestBody VRTourAddRequest vrTourAddRequest) {
        if (vrTourAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        VRTour vrTour = new VRTour();
        BeanUtils.copyProperties(vrTourAddRequest, vrTour);
        boolean result = vrTourService.save(vrTour);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newVRTourId = vrTour.getId();
        return ResultUtils.success(newVRTourId);
    }

    /**
     * 删除(仅管理员)
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteVRTour(@RequestBody DeleteRequest deleteRequest) {
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
        VRTour oldVRTour = vrTourService.getById(id);
        ThrowUtils.throwIf(oldVRTour == null, ErrorCode.NOT_FOUND_ERROR);
        boolean b = vrTourService.removeById(id);
        return ResultUtils.success(b);
    }
//
    /**
     * 更新（仅管理员）
     *
     * @param vrTourUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateVRTour(@RequestBody VRTourUpdateRequest vrTourUpdateRequest) {
        if (vrTourUpdateRequest == null || vrTourUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        VRTour vrTour = new VRTour();
        BeanUtils.copyProperties(vrTourUpdateRequest, vrTour);
        // 参数校验
        long id = vrTourUpdateRequest.getId();
        // 判断是否存在
        VRTour oldVRTour = vrTourService.getById(id);
        ThrowUtils.throwIf(oldVRTour == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = vrTourService.updateById(vrTour);
        return ResultUtils.success(result);
    }



    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<VRTour> getVRTourById(Long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        VRTour vrTour = vrTourService.getById(id);
        if (vrTour == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(vrTour);
    }

    /**
     *
     *
     * @param vrTourQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<VRTour>> listVRTourByPage(@RequestBody VRTourQueryRequest vrTourQueryRequest) {
        long current = vrTourQueryRequest.getCurrent();
        long size = vrTourQueryRequest.getPageSize();
        Page<VRTour> vrTourPage = vrTourService.page(new Page<>(current, size),
                vrTourService.getQueryWrapper(vrTourQueryRequest));
        return ResultUtils.success(vrTourPage);
    }

   
}
