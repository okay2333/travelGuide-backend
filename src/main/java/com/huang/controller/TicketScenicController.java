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
import com.huang.model.dto.ticketScenic.TicketScenicQueryRequest;
import com.huang.model.entity.TicketScenic;
import com.huang.model.vo.TicketScenicVO;
import com.huang.service.TicketScenicService;
import com.huang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 帖子接口
 *
 *
 *
 */
@RestController
@RequestMapping("/ticketScenic")
@Slf4j
public class TicketScenicController {

    @Resource
    private TicketScenicService ticketScenicService;

    @Resource
    private UserService userService;

//    // region 增删改查
//
//    /**
//     * 创建预约表（仅管理员）
//     *
//     * @param ticketScenicAddRequest
//     * @return
//     */
//    @PostMapping("/add")
//    public BaseResponse<Long> addTicketScenic(@RequestBody TicketScenicAddRequest ticketScenicAddRequest) {
//        if (ticketScenicAddRequest == null) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 先判断是否已登录
//        if (!StpUtil.isLogin()) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
//        }
//        TicketScenic ticketScenic = new TicketScenic();
//        BeanUtils.copyProperties(ticketScenicAddRequest, ticketScenic);
//
//        boolean result = ticketScenicService.save(ticketScenic);
//        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
//        long newTicketScenicId = ticketScenic.getId();
//        return ResultUtils.success(newTicketScenicId);
//    }
//
//    /**
//     * 删除
//     *
//     * @param deleteRequest
//     * @return
//     */
//    @PostMapping("/delete")
//    public BaseResponse<Boolean> deleteTicketScenic(@RequestBody DeleteRequest deleteRequest) {
//        if (deleteRequest == null || deleteRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 先判断是否已登录
//        if (!StpUtil.isLogin()) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
//        }
//        long id = deleteRequest.getId();
//        // 判断是否存在
//        TicketScenic oldTicketScenic = ticketScenicService.getById(id);
//
//        ThrowUtils.throwIf(oldTicketScenic == null, ErrorCode.NOT_FOUND_ERROR);
//        // 仅本人或管理员可删除
//        if (!userService.isAdmin(StpUtil.getLoginIdAsLong())) {
//            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//        }
//        boolean b = ticketScenicService.removeById(id);
//        return ResultUtils.success(b);
//    }
//
//    /**
//     * 更新（仅管理员）
//     *
//     * @param ticketScenicUpdateRequest
//     * @return
//     */
//    @PostMapping("/update")
//    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
//    public BaseResponse<Boolean> updateTicketScenic(@RequestBody TicketScenicUpdateRequest ticketScenicUpdateRequest) {
//        if (ticketScenicUpdateRequest == null || ticketScenicUpdateRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        TicketScenic ticketScenic = new TicketScenic();
//        BeanUtils.copyProperties(ticketScenicUpdateRequest, ticketScenic);
//
//        // 参数校验
//        ticketScenicService.validTicketScenic(ticketScenic, false);
//        long id = ticketScenicUpdateRequest.getId();
//        // 判断是否存在
//        TicketScenic oldTicketScenic = ticketScenicService.getById(id);
//        ThrowUtils.throwIf(oldTicketScenic == null, ErrorCode.NOT_FOUND_ERROR);
//        boolean result = ticketScenicService.updateById(ticketScenic);
//        return ResultUtils.success(result);
//    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<TicketScenicVO> getTicketScenicVOById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        TicketScenic ticketScenic = ticketScenicService.getById(id);
        if (ticketScenic == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(ticketScenicService.getTicketScenicVO(ticketScenic));
    }

    /**
     * 分页获取列表
     *
     * @param ticketScenicQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<TicketScenicVO>> listTicketScenicByPage(@RequestBody TicketScenicQueryRequest ticketScenicQueryRequest) {
        long current = ticketScenicQueryRequest.getCurrent();
        long size = ticketScenicQueryRequest.getPageSize();
        Page<TicketScenic> ticketScenicPage = ticketScenicService.page(new Page<>(current, size),
                ticketScenicService.getQueryWrapper(ticketScenicQueryRequest));
        return ResultUtils.success(ticketScenicService.getTicketScenicVOPage(ticketScenicPage));
    }

    


}
