package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.BaseResponse;
import com.huang.common.DeleteRequest;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.orders.OrdersAddRequest;
import com.huang.model.dto.orders.OrdersEditRequest;
import com.huang.model.dto.orders.OrdersQueryRequest;
import com.huang.model.entity.Orders;
import javax.annotation.Resource;


import com.huang.model.entity.Qrcodeverification;
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

        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersAddRequest, orders);
        orders.setUserId(StpUtil.getLoginIdAsLong());
        orders.setOrderId(String.valueOf(UUID.randomUUID()));
        orders.setOrderStatus(0);
        boolean result = ordersService.save(orders);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
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

        // 仅本人或管理员可删除
        if (!oldOrder.getUserId().equals(StpUtil.getLoginIdAsLong()) && !userService.isAdmin(StpUtil.getLoginIdAsLong())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        boolean result = ordersService.removeById(id);
        return ResultUtils.success(result);
    }

    /**
     * 更新订单
     *
     * @param orderEditRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateOrders(@RequestBody OrdersEditRequest orderEditRequest) throws IOException {
        System.out.println("为什么不输出"+orderEditRequest);
        if (orderEditRequest == null || orderEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Orders orders = new Orders();
        BeanUtils.copyProperties(orderEditRequest, orders);
        long id = orderEditRequest.getId();

        // 判断是否存在
        Orders oldOrder = ordersService.getById(id);
        ThrowUtils.throwIf(oldOrder == null, ErrorCode.NOT_FOUND_ERROR);

        // 仅本人或管理员可更新
        if (!oldOrder.getUserId().equals(StpUtil.getLoginIdAsLong()) && !userService.isAdmin(StpUtil.getLoginIdAsLong())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        boolean result = ordersService.updateById(orders);
        if (orderEditRequest.getOrderStatus() == 1) {
            System.out.println("支付成功生成二维码");
            BufferedImage bufferedImage = QRCodeUtil.generateQRCode("订单号:" + oldOrder.getOrderId());
            System.out.println("二维码生成成功！");
            String QRCodeUrl = fileService.uploadQRCodeToOss(bufferedImage);
//          插入二维码地址
            Qrcodeverification qrcodeverification = new Qrcodeverification();
            qrcodeverification.setOrderId(oldOrder.getOrderId());
            qrcodeverification.setQrCode(QRCodeUrl);
            qrcodeverification.setIsVerified(0);
            qrcodeverification.setVerificationDate(null);
            boolean QRCodeResult = qrcodeverificationService.save(qrcodeverification);
            System.out.println("插入后结果"+QRCodeResult);
            System.out.println(QRCodeUrl);
        }
        return ResultUtils.success(result);
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

    // endregion

 
}
