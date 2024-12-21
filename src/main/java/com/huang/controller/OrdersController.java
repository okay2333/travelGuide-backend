package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.BaseResponse;
import com.huang.common.DeleteRequest;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.constant.OrdersConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.orders.OrdersAddRequest;
import com.huang.model.dto.orders.OrdersUpdateRequest;
import com.huang.model.dto.orders.OrdersQueryRequest;
import com.huang.model.dto.scenic.ScenicQueryRequest;
import com.huang.model.dto.ticket.TicketUpdateRequest;
import com.huang.model.entity.Orders;
import javax.annotation.Resource;


import com.huang.model.entity.Qrcodeverification;
import com.huang.model.entity.Scenic;
import com.huang.model.entity.Ticket;
import com.huang.model.vo.OrdersVO;
import com.huang.model.vo.ScenicVO;
import com.huang.service.FileService;
import com.huang.service.OrdersService;
import com.huang.service.QrcodeverificationService;
import com.huang.service.UserService;
import com.huang.utils.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 订单接口
 */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @Resource
    private UserService userService;
    @Resource
    private FileService fileService;
    @Resource
    private QrcodeverificationService qrcodeverificationService;
    // region 增删改查
    /**
     * 创建订单
     *
     * @param ordersAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Orders> addOrders(@RequestBody OrdersAddRequest ordersAddRequest) {
        System.out.println(ordersAddRequest);
        if (ordersAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 创建订单实时库存解决方案
        Orders orders = ordersService.createOrders(ordersAddRequest);
        return ResultUtils.success(orders);
    }

    /**
     * 删除订单
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteOrders(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        long id = deleteRequest.getId();
        Orders oldOrder = ordersService.getById(id);
        ThrowUtils.throwIf(oldOrder == null, ErrorCode.NOT_FOUND_ERROR);

        boolean result = ordersService.removeById(id);
        return ResultUtils.success(result);
    }


    /**
     * 更新订单（仅管理员）
     *
     * @param ordersUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateOrders(@RequestBody OrdersUpdateRequest ordersUpdateRequest) throws IOException {
        if (ordersUpdateRequest == null || ordersUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersUpdateRequest, orders);

        // 参数校验
        long id = ordersUpdateRequest.getId();
        // 判断是否存在
        Orders oldOrders = ordersService.getById(id);
        ThrowUtils.throwIf(oldOrders == null, ErrorCode.NOT_FOUND_ERROR);
//        boolean result = ordersService.updateById(orders);
//        return ResultUtils.success(result);
        return ordersService.updateOrders(orders);
    }


    /**
     * 根据 id 获取订单
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Orders> getOrdersById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Orders orders = ordersService.getById(id);
        if (orders == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(orders);
    }

    /**
     * 分页获取用户的订单列表
     *
     * @param ordersQueryRequest
     * @return
     */
    @PostMapping("/my/list/page")
    public BaseResponse<Page<Orders>> listMyOrdersByPage(@RequestBody OrdersQueryRequest ordersQueryRequest) {
        if (ordersQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        ordersQueryRequest.setUserId(StpUtil.getLoginIdAsLong());
        long current = ordersQueryRequest.getCurrent();
        long size = ordersQueryRequest.getPageSize();

        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Orders> orderPage = ordersService.page(new Page<>(current, size),
                ordersService.getQueryWrapper(ordersQueryRequest));

        return ResultUtils.success(orderPage);
    }


    /**
     * 分页获取列表（封装类）
     *
     * @param ordersQueryRequest
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<OrdersVO>> listOrdersVOByPage(@RequestBody OrdersQueryRequest ordersQueryRequest) {
        long current = ordersQueryRequest.getCurrent();
        long size = ordersQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 101, ErrorCode.PARAMS_ERROR);
        Page<Orders> scenicPage = ordersService.page(new Page<>(current, size),
                ordersService.getQueryWrapper(ordersQueryRequest));
        return ResultUtils.success(ordersService.getOrdersVOPage(scenicPage));
    }

    /**
     * 分页个人获取列表（封装类）
     *
     * @return
     */
    @PostMapping("/list/page/my/vo")
    public BaseResponse<List<OrdersVO>> listMyOrdersVOByPage() {
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", StpUtil.getLoginIdAsLong());
        List<Orders> ordersList = ordersService.list(queryWrapper);
        return ResultUtils.success(ordersService.getOrdersVO(ordersList));
    }


    @GetMapping("/count")
    public BaseResponse<Long> getUserOrdersCount() {
        // 获取当前登录用户ID
        Long userId = StpUtil.getLoginIdAsLong();
        long count = ordersService.countUserOrders(userId);
        return ResultUtils.success(count);
    }
    // endregion

 
}
