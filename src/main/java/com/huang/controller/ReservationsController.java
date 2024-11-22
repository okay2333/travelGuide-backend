package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.annotation.AuthCheck;
import com.huang.common.BaseResponse;
import com.huang.common.DeleteRequest;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.constant.UserConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.reservations.ReservationsQueryRequest;
import com.huang.model.dto.reservations.ReservationsAddRequest;
import com.huang.model.dto.reservations.ReservationsUpdateRequest;
import com.huang.model.entity.Reservations;
import com.huang.model.vo.ReservationsVO;
import com.huang.service.ReservationsService;
import com.huang.service.UserService;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 帖子接口
 *
 *
 *
 */
@RestController
@RequestMapping("/reservations")
@Slf4j
public class ReservationsController {

    @Resource
    private ReservationsService reservationsService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建预约表（仅管理员）
     *
     * @param reservationsAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addReservations(@RequestBody ReservationsAddRequest reservationsAddRequest) {
        if (reservationsAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Reservations reservations = new Reservations();
        BeanUtils.copyProperties(reservationsAddRequest, reservations);

        boolean result = reservationsService.save(reservations);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newReservationsId = reservations.getId();
        return ResultUtils.success(newReservationsId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteReservations(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        long id = deleteRequest.getId();
        // 判断是否存在
        Reservations oldReservations = reservationsService.getById(id);

        ThrowUtils.throwIf(oldReservations == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!userService.isAdmin(StpUtil.getLoginIdAsLong())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = reservationsService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param reservationsUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateReservations(@RequestBody ReservationsUpdateRequest reservationsUpdateRequest) {
        if (reservationsUpdateRequest == null || reservationsUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Reservations reservations = new Reservations();
        BeanUtils.copyProperties(reservationsUpdateRequest, reservations);

        // 参数校验
        reservationsService.validReservations(reservations, false);
        long id = reservationsUpdateRequest.getId();
        // 判断是否存在
        Reservations oldReservations = reservationsService.getById(id);
        ThrowUtils.throwIf(oldReservations == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = reservationsService.updateById(reservations);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<ReservationsVO> getReservationsVOById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Reservations reservations = reservationsService.getById(id);
        if (reservations == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(reservationsService.getReservationsVO(reservations));
    }

    /**
     * 分页获取列表
     *
     * @param reservationsQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<Reservations>> listReservationsByPage(@RequestBody ReservationsQueryRequest reservationsQueryRequest) {
        long current = reservationsQueryRequest.getCurrent();
        long size = reservationsQueryRequest.getPageSize();
        Page<Reservations> reservationsPage = reservationsService.page(new Page<>(current, size),
                reservationsService.getQueryWrapper(reservationsQueryRequest));
        return ResultUtils.success(reservationsPage);
    }







}
