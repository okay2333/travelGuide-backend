package com.huang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.huang.model.dto.orders.OrdersQueryRequest;
import com.huang.model.entity.Orders;


import com.huang.mapper.OrdersMapper;
import com.huang.service.OrdersService;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;


import org.springframework.stereotype.Service;


/**
 * 订单服务实现
 */
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {




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
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "user_id", userId);
        return queryWrapper;
    }


}
