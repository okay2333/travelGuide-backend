package com.huang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.BaseResponse;
import com.huang.model.dto.orders.OrdersAddRequest;
import com.huang.model.dto.orders.OrdersQueryRequest;
import com.huang.model.entity.Orders;
import com.huang.model.vo.OrdersVO;
import com.huang.model.vo.TicketVO;

import java.io.IOException;
import java.util.List;

public interface OrdersService extends IService<Orders> {



    QueryWrapper<Orders> getQueryWrapper(OrdersQueryRequest ordersQueryRequest);

    Orders  createOrders(OrdersAddRequest ordersAddRequest);

    BaseResponse<Boolean> updateOrders(Orders orders) throws IOException;

    Boolean updateOrderStatus(Long orderId, Integer status) throws IOException;


    Page<OrdersVO>  getOrdersVOPage(Page<Orders> scenicPage);

    List<OrdersVO> getOrdersVO(List<Orders> ordersList);

    long countUserOrders(Long userId);
}


