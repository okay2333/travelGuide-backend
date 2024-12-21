package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.BaseResponse;
import com.huang.common.DeleteRequest;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.reservations.ReservationsQueryRequest;
import com.huang.model.dto.reservationsTime.Traveler;
import com.huang.model.dto.reservationsTravelers.ReservationsTravelersAddRequest;
import com.huang.model.entity.ReservationsTime;
import com.huang.model.entity.ReservationsTravelers;
import com.huang.model.vo.ReservationsTravelersVO;
import com.huang.service.ReservationsService;
import com.huang.service.ReservationsTravelersService;
import com.huang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 帖子接口
 *
 *
 *
 */
@RestController
@RequestMapping("/reservationsTravelers")
@Slf4j
public class ReservationsTravelersController {

    @Resource
    private ReservationsTravelersService reservationsTravelersService;

    @Resource
    private ReservationsService reservationsService;

    // region 增删改查

    /**
     * 创建预约表
     *
     * @param reservationsTravelersAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addReservationsTime(
            @RequestBody ReservationsTravelersAddRequest reservationsTravelersAddRequest) {
        if (reservationsTravelersAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        reservationsTravelersService.validAddRequest(reservationsTravelersAddRequest);
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        for (Traveler traveler : reservationsTravelersAddRequest.getTravelerList()) {
            ReservationsTravelers reservationsTravelers = new ReservationsTravelers();
            reservationsTravelers.setReservationsId(reservationsTravelersAddRequest.getReservationsId());
            reservationsTravelers.setIdNumber(traveler.getIdNumber());
            reservationsTravelers.setFullName(traveler.getFullName());
            reservationsTravelers.setPhoneNumber(traveler.getPhoneNumber());
            reservationsTravelers.setIsDelete(0);
            reservationsTravelers.setUserId(StpUtil.getLoginIdAsLong());
            reservationsTravelersService.save(reservationsTravelers);
        }
        // TODO 事务修改预约数量
        reservationsService.updateReservationsCount("add", reservationsTravelersAddRequest.getReservationsId(),reservationsTravelersAddRequest.getTravelerList().size());
        return ResultUtils.success(1L);
    }

    /**
     * 删除 预约记录
     *
     * @param deleteRequest
     * @return
     */
     @PostMapping("/delete")
     public BaseResponse<Boolean> deleteReservationsTime(@RequestBody
                                                         DeleteRequest deleteRequest) {
     if (deleteRequest == null || deleteRequest.getId() <= 0) {
     throw new BusinessException(ErrorCode.PARAMS_ERROR);
     }
     // 先判断是否已登录
     if (!StpUtil.isLogin()) {
     throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
     }
     long reservationsId = deleteRequest.getId();

     boolean b = reservationsTravelersService.deleteByReservationsId(reservationsId);
     return ResultUtils.success(b);
     }

    /**
     * 更新（仅管���员）
     *
     * @param ReservationsTimeUpdateRequest
     * @return
     */
    // @PostMapping("/update")
    // @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    // public BaseResponse<Boolean> updateReservationsTime(@RequestBody
    // ReservationsTimeUpdateRequest ReservationsTimeUpdateRequest) {
    // if (ReservationsTimeUpdateRequest == null ||
    // ReservationsTimeUpdateRequest.getId() <= 0) {
    // throw new BusinessException(ErrorCode.PARAMS_ERROR);
    // }
    // ReservationsTime ReservationsTime = new ReservationsTime();
    // BeanUtils.copyProperties(ReservationsTimeUpdateRequest, ReservationsTime);
    //
    // // 参数校验
    // ReservationsTimeService.validReservationsTime(ReservationsTime, false);
    // long id = ReservationsTimeUpdateRequest.getId();
    // // 判断是否存在
    // ReservationsTime oldReservationsTime = ReservationsTimeService.getById(id);
    // ThrowUtils.throwIf(oldReservationsTime == null, ErrorCode.NOT_FOUND_ERROR);
    // boolean result = ReservationsTimeService.updateById(ReservationsTime);
    // return ResultUtils.success(result);
    // }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    // @GetMapping("/get/vo")
    // public BaseResponse<ReservationsTimeVO> getReservationsTimeVOById(long id) {
    // if (id <= 0) {
    // throw new BusinessException(ErrorCode.PARAMS_ERROR);
    // }
    // ReservationsTime ReservationsTime = ReservationsTimeService.getById(id);
    // if (ReservationsTime == null) {
    // throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
    // }
    // return
    // ResultUtils.success(ReservationsTimeService.getReservationsTimeVO(ReservationsTime));
    // }

    // /**
    // * 分页获取列表
    // *
    // * @param ReservationsTimeQueryRequest
    // * @return
    // */
    // @PostMapping("/list/page")
    // public BaseResponse<Page<ReservationsTime>>
    // listReservationsTimeByPage(@RequestBody ReservationsTimeQueryRequest
    // ReservationsTimeQueryRequest) {
    // long current = ReservationsTimeQueryRequest.getCurrent();
    // long size = ReservationsTimeQueryRequest.getPageSize();
    // Page<ReservationsTime> ReservationsTimePage =
    // ReservationsTimeService.page(new Page<>(current, size),
    // ReservationsTimeService.getQueryWrapper(ReservationsTimeQueryRequest));
    // return ResultUtils.success(ReservationsTimePage);
    // }
    //

    /**
     * 分页获取列表
     *
     * @return
     */
    @PostMapping("/list/page/byUserId")
    public BaseResponse<List<ReservationsTravelersVO>> listReservationsTimeByUserId() {
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        List<ReservationsTravelersVO> reservationsTravelers = reservationsTravelersService.byUserId();
        return ResultUtils.success(reservationsTravelers);
    }
    //

    @GetMapping("/count")
    public BaseResponse<Long> getUserReservationsCount() {
        // 获取当前登录用户ID
        Long userId = StpUtil.getLoginIdAsLong();
        long count = reservationsTravelersService.countUserReservations(userId);
        return ResultUtils.success(count);
    }

}
