package com.huang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.orders.OrdersQueryRequest;
import com.huang.model.entity.Orders;

public interface OrdersService extends IService<Orders> {



    QueryWrapper<Orders> getQueryWrapper(OrdersQueryRequest ordersQueryRequest);

}


