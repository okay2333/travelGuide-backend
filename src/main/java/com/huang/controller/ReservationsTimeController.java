package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.BaseResponse;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.exception.BusinessException;
import com.huang.model.dto.reservations.ReservationsQueryRequest;
import com.huang.model.dto.reservationsTime.ReservationsTimeAddRequest;
import com.huang.model.dto.reservationsTime.ReservationsTimeQueryRequest;
import com.huang.model.entity.Reservations;
import com.huang.model.entity.ReservationsTime;
import com.huang.service.ReservationsTimeService;
import com.huang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 帖子接口
 *
 *
 *
 */
@RestController
@RequestMapping("/reservationsTime")
@Slf4j
public class ReservationsTimeController {

    @Resource
    private ReservationsTimeService reservationsTimeService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建预约表（仅管理员）
     *
     * @param reservationsTimeAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addReservationsTime(@RequestBody ReservationsTimeAddRequest reservationsTimeAddRequest) {
        if (reservationsTimeAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        ReservationsTime ReservationsTime = new ReservationsTime();
        BeanUtils.copyProperties(reservationsTimeAddRequest, ReservationsTime);
        System.out.println("@@@@@@@@,开放时间");
//        TODO 传入时间格式不对，组件ID冲突
        System.out.println(reservationsTimeAddRequest.getOpenDateTimes());
        for (Date openDateTime : reservationsTimeAddRequest.getOpenDateTimes()) {
            ReservationsTime reservationsTime = new ReservationsTime();
            reservationsTime.setReservationsId(reservationsTimeAddRequest.getReservationsId());
            reservationsTime.setOpenDateTime(openDateTime);
            reservationsTime.setStock(reservationsTimeAddRequest.getStock());
            reservationsTime.setInstructions(reservationsTimeAddRequest.getInstructions());
            reservationsTime.setCreateTime(new Date());
            reservationsTime.setUpdateTime(new Date());
            reservationsTime.setIsDelete(0);
            reservationsTimeService.save(ReservationsTime);
        }
        return ResultUtils.success(1L);
    }


    /**
     * 分页获取列表
     *
     * @param reservationsTimeQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<ReservationsTime>> listReservationsTimeByPage(@RequestBody ReservationsTimeQueryRequest reservationsTimeQueryRequest) {
        long current = reservationsTimeQueryRequest.getCurrent();
        long size = reservationsTimeQueryRequest.getPageSize();
        Page<ReservationsTime> reservationsPage = reservationsTimeService.page(new Page<>(current, size),
                reservationsTimeService.getQueryWrapper(reservationsTimeQueryRequest));
        return ResultUtils.success(reservationsPage);
    }



    /**
     * 删除
     *
     * @param deleteRequest
     * @return
     */
//    @PostMapping("/delete")
//    public BaseResponse<Boolean> deleteReservationsTime(@RequestBody DeleteRequest deleteRequest) {
//        if (deleteRequest == null || deleteRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 先判断是否已登录
//        if (!StpUtil.isLogin()) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
//        }
//        long id = deleteRequest.getId();
//        // 判断是否存在
//        ReservationsTime oldReservationsTime = ReservationsTimeService.getById(id);
//
//        ThrowUtils.throwIf(oldReservationsTime == null, ErrorCode.NOT_FOUND_ERROR);
//        // 仅本人或管理员可删除
//        if (!userService.isAdmin(StpUtil.getLoginIdAsLong())) {
//            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//        }
//        boolean b = ReservationsTimeService.removeById(id);
//        return ResultUtils.success(b);
//    }

    /**
     * 更新（仅管理员）
     *
     * @param ReservationsTimeUpdateRequest
     * @return
     */
//    @PostMapping("/update")
//    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
//    public BaseResponse<Boolean> updateReservationsTime(@RequestBody ReservationsTimeUpdateRequest ReservationsTimeUpdateRequest) {
//        if (ReservationsTimeUpdateRequest == null || ReservationsTimeUpdateRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        ReservationsTime ReservationsTime = new ReservationsTime();
//        BeanUtils.copyProperties(ReservationsTimeUpdateRequest, ReservationsTime);
//
//        // 参数校验
//        ReservationsTimeService.validReservationsTime(ReservationsTime, false);
//        long id = ReservationsTimeUpdateRequest.getId();
//        // 判断是否存在
//        ReservationsTime oldReservationsTime = ReservationsTimeService.getById(id);
//        ThrowUtils.throwIf(oldReservationsTime == null, ErrorCode.NOT_FOUND_ERROR);
//        boolean result = ReservationsTimeService.updateById(ReservationsTime);
//        return ResultUtils.success(result);
//    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
//    @GetMapping("/get/vo")
//    public BaseResponse<ReservationsTimeVO> getReservationsTimeVOById(long id) {
//        if (id <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        ReservationsTime ReservationsTime = ReservationsTimeService.getById(id);
//        if (ReservationsTime == null) {
//            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
//        }
//        return ResultUtils.success(ReservationsTimeService.getReservationsTimeVO(ReservationsTime));
//    }

//    /**
//     * 分页获取列表
//     *
//     * @param ReservationsTimeQueryRequest
//     * @return
//     */
//    @PostMapping("/list/page")
//    public BaseResponse<Page<ReservationsTime>> listReservationsTimeByPage(@RequestBody ReservationsTimeQueryRequest ReservationsTimeQueryRequest) {
//        long current = ReservationsTimeQueryRequest.getCurrent();
//        long size = ReservationsTimeQueryRequest.getPageSize();
//        Page<ReservationsTime> ReservationsTimePage = ReservationsTimeService.page(new Page<>(current, size),
//                ReservationsTimeService.getQueryWrapper(ReservationsTimeQueryRequest));
//        return ResultUtils.success(ReservationsTimePage);
//    }
//






}
