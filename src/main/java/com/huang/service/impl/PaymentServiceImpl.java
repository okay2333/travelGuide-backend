package com.huang.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.ErrorCode;
import com.huang.constant.CommonConstant;
import com.huang.constant.PaymentConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.mapper.PaymentMapper;
import com.huang.mapper.PostFavourMapper;
import com.huang.mapper.PostThumbMapper;
import com.huang.mapper.ScenicMapper;
import com.huang.model.dto.post.PostQueryRequest;
import com.huang.model.dto.scenic.ScenicQueryRequest;
import com.huang.model.entity.Payment;
import com.huang.model.entity.Post;
import com.huang.model.entity.Scenic;
import com.huang.model.vo.ScenicVO;
import com.huang.service.OrdersService;
import com.huang.service.PaymentService;
import com.huang.service.ScenicService;
import com.huang.service.UserService;
import com.huang.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 支付管理实现
 *
 * 
 * 
 */
@Service
@Slf4j
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

    @Autowired
    private OrdersService ordersService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePayment(Payment payment) throws IOException {
        boolean result = this.updateById(payment);
        if (payment.getPaymentStatus().equals(PaymentConstant.STATUS_SUCCESS)) {
            // 支付成功
            // todo 业务逻辑
            result = ordersService.updateOrderStatus(payment.getOrderId(), PaymentConstant.STATUS_SUCCESS);
            ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
            return true;
        } else if(payment.getPaymentStatus().equals(PaymentConstant.STATUS_FAILED)){
            // 支付失败
            // todo 业务逻辑
            return false;
        }
        return result;
    }
}




