package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.post.PostQueryRequest;
import com.huang.model.dto.scenic.ScenicQueryRequest;
import com.huang.model.entity.Payment;
import com.huang.model.entity.Post;
import com.huang.model.entity.Scenic;
import com.huang.model.vo.ScenicVO;

import java.io.IOException;

/**
 * 支付管理
 *
 * 
 * 
 */
public interface PaymentService extends IService<Payment> {


    boolean updatePayment(Payment payment) throws IOException;
}
