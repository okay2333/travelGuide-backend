package com.huang.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.huang.common.BaseResponse;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.constant.OrdersConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.orders.OrdersAddRequest;
import com.huang.model.dto.orders.OrdersQueryRequest;
import com.huang.model.entity.*;


import com.huang.mapper.OrdersMapper;
import com.huang.model.vo.OrdersVO;
import com.huang.model.vo.ScenicVO;
import com.huang.model.vo.TicketVO;
import com.huang.model.vo.UserVO;
import com.huang.service.*;


import com.huang.utils.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 订单服务实现
 */
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Resource
    OrdersMapper ordersMapper;
    @Resource
    private UserService userService;
    @Resource
    private TicketService ticketService;

    @Resource
    private QrcodeverificationService qrcodeverificationService;

    @Resource
    private ScenicService scenicService;

    @Resource
    private FileService fileService;
    /**
     * 获取查询包装类
     */
    @Override
    public QueryWrapper<Orders> getQueryWrapper(OrdersQueryRequest orderQueryRequest) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        if (orderQueryRequest == null) {
            return queryWrapper;
        }
        Long userId = orderQueryRequest.getUserId();

        // 拼接查询条件
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);

        return queryWrapper;
    }

    @Override
    public Page<OrdersVO> getOrdersVOPage(Page<Orders> ordersPage) {
        List<Orders> ordersList = ordersPage.getRecords();
        Page<OrdersVO> ordersVOPage = new Page<>(ordersPage.getCurrent(), ordersPage.getSize(), ordersPage.getTotal());
        if (CollUtil.isEmpty(ordersList)) {
            return ordersVOPage;
        }

        // 1. 关联查询用户信息
        Set<Long> userIdSet = ordersList.stream().map(Orders::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap = userService.listByIds(userIdSet).stream()
                .collect(Collectors.groupingBy(User::getId));
        // 2. 关联查询票务信息
        Set<Long> ticketIdSet = ordersList.stream().map(Orders::getTicketId).collect(Collectors.toSet());
        Map<Long, List<Ticket>> ticketIdTicketListMap = ticketService.listByIds(ticketIdSet).stream()
                .collect(Collectors.groupingBy(Ticket::getId));

        // 3. 关联查询景区信息
        Set<Long> scenicIdSet = ticketIdTicketListMap.values().stream()
                .flatMap(List::stream)
                .map(Ticket::getScenicId)
                .collect(Collectors.toSet());
        Map<Long, List<Scenic>> scenicIdScenicListMap = scenicService.listByIds(scenicIdSet).stream()
                .collect(Collectors.groupingBy(Scenic::getId));

        // 填充信息
        List<OrdersVO> ordersVOList = ordersList.stream().map(order -> {
            OrdersVO ordersVO = new OrdersVO();
            BeanUtils.copyProperties(order, ordersVO);
            long userId = order.getUserId();
            User user = null;
            if (userIdUserListMap.containsKey(userId)) {
                user = userIdUserListMap.get(userId).get(0);
            }

            if (ticketIdTicketListMap.containsKey(order.getTicketId())) {
                Ticket ticket = ticketIdTicketListMap.get(order.getTicketId()).get(0);
                TicketVO ticketVO = TicketVO.objToVo(ticket);
                if (scenicIdScenicListMap.containsKey(ticket.getScenicId())) {
                    Scenic scenic = scenicIdScenicListMap.get(ticket.getScenicId()).get(0);
                    ticketVO.setScenicVO(ScenicVO.objToVo(scenic));
                }
                ordersVO.setTicketVO(ticketVO);
            }
            ordersVO.setUserVO(UserVO.objToVo(user));
            return ordersVO;
        }).collect(Collectors.toList());

        ordersVOPage.setRecords(ordersVOList);
        return ordersVOPage;
    }

    @Override
    public List<OrdersVO> getOrdersVO(List<Orders> ordersList) {

        // 1. 关联查询用户信息
        Set<Long> userIdSet = ordersList.stream().map(Orders::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap = userService.listByIds(userIdSet).stream()
                .collect(Collectors.groupingBy(User::getId));
        // 2. 关联查询票务信息
        Set<Long> ticketIdSet = ordersList.stream().map(Orders::getTicketId).collect(Collectors.toSet());
        Map<Long, List<Ticket>> ticketIdTicketListMap = ticketService.listByIds(ticketIdSet).stream()
                .collect(Collectors.groupingBy(Ticket::getId));

        // 3. 关联查询景区信息
        Set<Long> scenicIdSet = ticketIdTicketListMap.values().stream()
                .flatMap(List::stream)
                .map(Ticket::getScenicId)
                .collect(Collectors.toSet());
        Map<Long, List<Scenic>> scenicIdScenicListMap = scenicService.listByIds(scenicIdSet).stream()
                .collect(Collectors.groupingBy(Scenic::getId));

        // 填充信息
        List<OrdersVO> ordersVOList = ordersList.stream().map(order -> {
            OrdersVO ordersVO = new OrdersVO();
            BeanUtils.copyProperties(order, ordersVO);
            long userId = order.getUserId();
            User user = null;
            if (userIdUserListMap.containsKey(userId)) {
                user = userIdUserListMap.get(userId).get(0);
            }

            if (ticketIdTicketListMap.containsKey(order.getTicketId())) {
                Ticket ticket = ticketIdTicketListMap.get(order.getTicketId()).get(0);
                TicketVO ticketVO = TicketVO.objToVo(ticket);
                if (scenicIdScenicListMap.containsKey(ticket.getScenicId())) {
                    Scenic scenic = scenicIdScenicListMap.get(ticket.getScenicId()).get(0);
                    ticketVO.setScenicVO(ScenicVO.objToVo(scenic));
                }
                ordersVO.setTicketVO(ticketVO);
            }
            ordersVO.setUserVO(UserVO.objToVo(user));
            return ordersVO;
        }).collect(Collectors.toList());

        return ordersVOList;
    }

    @Override
    public long countUserOrders(Long userId) {
        return ordersMapper.countUserOrders(userId);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Orders createOrders(OrdersAddRequest ordersAddRequest) {
        //1. 检查商品是否存在
        Ticket ticket = ticketService.getById(ordersAddRequest.getTicketId());
        if (ticket == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        //2. 检查商品库存是否足够
        if (ticket.getStock() < ordersAddRequest.getQuantity()) {
            throw new BusinessException(ErrorCode.STOCK_NOT_ENOUGH_ERROR);
        }
        //3. 扣减库存
        ticket.setStock(ticket.getStock() - ordersAddRequest.getQuantity());
        ticketService.updateById(ticket);
        //4. 创建订单
        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersAddRequest, orders);
        orders.setUserId(StpUtil.getLoginIdAsLong());
        orders.setId(Long.parseLong(IdUtil.getSnowflakeNextIdStr()));
        orders.setOrderStatus(OrdersConstant.STATUS_PENDING);
        boolean result = this.save(orders);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return orders;
    }

    @Override
    public BaseResponse<Boolean> updateOrders(Orders orders) throws IOException {

        boolean result = this.updateById(orders);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        if (orders.getOrderStatus() == OrdersConstant.STATUS_PAID) {
            // 支付成功 生成二维码
            BufferedImage bufferedImage = QRCodeUtil.generateQRCode("订单号:" + orders.getId());
            System.out.println("二维码生成成功！");
            String QRCodeUrl = fileService.uploadQRCodeToOss(bufferedImage);
            //插入二维码地址
            Qrcodeverification qrcodeverification = new Qrcodeverification();
            qrcodeverification.setOrderId(orders.getId());
            qrcodeverification.setQrCode(QRCodeUrl);
            qrcodeverification.setIsVerified(0);
            boolean QRCodeResult = qrcodeverificationService.save(qrcodeverification);
            if (!QRCodeResult) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR);
            }
            return ResultUtils.success(result,"支付成功，二维码生成成功！");
        } else if(orders.getOrderStatus() == OrdersConstant.STATUS_CANCELLED){
            // 订单取消
            System.out.println("订单取消");
            return ResultUtils.success(true,"订单取消成功！");
        }else{
            // 订单状态不正确
            System.out.println("订单状态不正确");
            return ResultUtils.error(ErrorCode.OPERATION_ERROR,"订单状态不正确！");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateOrderStatus(Long orderId, Integer status) throws IOException {
        Orders orders = new Orders();
        orders.setId(orderId);
        orders.setOrderStatus(status);
        boolean result = this.updateById(orders);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        if (orders.getOrderStatus() == OrdersConstant.STATUS_PAID) {
            // 支付成功 生成二维码
            BufferedImage bufferedImage = QRCodeUtil.generateQRCode("订单号:" + orders.getId());
            System.out.println("二维码生成成功！");
            String QRCodeUrl = fileService.uploadQRCodeToOss(bufferedImage);
            //插入二维码地址
            Qrcodeverification qrcodeverification = new Qrcodeverification();
            qrcodeverification.setOrderId(orders.getId());
            qrcodeverification.setQrCode(QRCodeUrl);
            qrcodeverification.setIsVerified(0);
            boolean QRCodeResult = qrcodeverificationService.save(qrcodeverification);
            if (!QRCodeResult) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR);
            }
            return true;
        }
        return result;
    }


}
