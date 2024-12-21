package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.BaseResponse;
import com.huang.common.DeleteRequest;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.ticket.TicketAddRequest;
import com.huang.model.dto.ticket.TicketQueryRequest;
import com.huang.model.dto.ticket.TicketUpdateRequest;
import com.huang.model.entity.Ticket;
import com.huang.model.vo.TicketVO;
import com.huang.service.TicketService;
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
@RequestMapping("/ticket")
@Slf4j
public class TicketController {

    @Resource
    private TicketService ticketService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建预约表（仅管理员）
     *
     * @param ticketAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addTicket(@RequestBody TicketAddRequest ticketAddRequest) {
        if (ticketAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketAddRequest, ticket);

        boolean result = ticketService.save(ticket);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newTicketId = ticket.getId();
        return ResultUtils.success(newTicketId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTicket(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        long id = deleteRequest.getId();
        // 判断是否存在
        Ticket oldTicket = ticketService.getById(id);

        ThrowUtils.throwIf(oldTicket == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!userService.isAdmin(StpUtil.getLoginIdAsLong())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = ticketService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param ticketUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateTicket(@RequestBody TicketUpdateRequest ticketUpdateRequest) {
        if (ticketUpdateRequest == null || ticketUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketUpdateRequest, ticket);

        // 参数校验
        ticketService.validTicket(ticket, false);
        long id = ticketUpdateRequest.getId();
        // 判断是否存在
        Ticket oldTicket = ticketService.getById(id);
        ThrowUtils.throwIf(oldTicket == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = ticketService.updateById(ticket);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<TicketVO> getTicketVOById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Ticket ticket = ticketService.getById(id);
        if (ticket == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(ticketService.getTicketVO(ticket));
    }

    /**
     * 分页获取列表
     *
     * @param ticketQueryRequest
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<TicketVO>> listTicketByPage(@RequestBody TicketQueryRequest ticketQueryRequest) {
        long current = ticketQueryRequest.getCurrent();
        long size = ticketQueryRequest.getPageSize();
        Page<Ticket> ticketPage = ticketService.page(new Page<>(current, size),
                ticketService.getQueryWrapper(ticketQueryRequest));
        return ResultUtils.success(ticketService.getTicketVOPage(ticketPage));
    }

}
