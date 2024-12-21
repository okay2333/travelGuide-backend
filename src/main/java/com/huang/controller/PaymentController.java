package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.BaseResponse;
import com.huang.common.DeleteRequest;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.constant.UserConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.payment.PaymentAddRequest;
import com.huang.model.dto.payment.PaymentUpdateRequest;
import com.huang.model.entity.Payment;
import com.huang.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 支付接口
 *
 * 
 * 
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;


    // region 增删改查

    /**
     * 创建
     *
     * @param paymentAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addPayment(@RequestBody PaymentAddRequest paymentAddRequest) {
        if (paymentAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentAddRequest, payment);
        boolean result = paymentService.save(payment);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newPaymentId = payment.getId();
        return ResultUtils.success(newPaymentId);
    }

    /**
     * 更新
     *
     * @param paymentUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updatePayment(@RequestBody PaymentUpdateRequest paymentUpdateRequest) throws IOException {
        if (paymentUpdateRequest == null || paymentUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentUpdateRequest, payment);

        long id = paymentUpdateRequest.getId();
        // 判断是否存在
        Payment oldPayment = paymentService.getById(id);
        ThrowUtils.throwIf(oldPayment == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = paymentService.updatePayment(payment);
//        boolean result = paymentService.updateById(payment);
        return ResultUtils.success(result);
    }


}
