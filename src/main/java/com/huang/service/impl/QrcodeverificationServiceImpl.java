package com.huang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.mapper.OrdersMapper;
import com.huang.mapper.QrcodeverificationMapper;
import com.huang.model.dto.orders.OrdersQueryRequest;
import com.huang.model.entity.Orders;
import com.huang.model.entity.Qrcodeverification;
import com.huang.service.OrdersService;
import com.huang.service.QrcodeverificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;


/**
 * 订单服务实现
 */
@Service
@Slf4j
public class QrcodeverificationServiceImpl extends ServiceImpl<QrcodeverificationMapper, Qrcodeverification> implements QrcodeverificationService {







}
