package com.huang.constant;


/**
 * 支付常量
 *
 *
 *
 */
public interface PaymentConstant {

    /**
     * 支付状态键
     */
    String PAYMENT_STATUS_KEY = "payment_status";

    // region 支付状态

    /**
     * 支付成功
     */
    Integer STATUS_SUCCESS = 1;

    /**
     * 支付失败
     */
    Integer STATUS_FAILED = 0;

    // endregion

    // region 支付方式

    /**
     * 信用卡支付
     */
    String METHOD_CREDIT_CARD = "credit_card";

    /**
     * 支付宝支付
     */
    String METHOD_ALIPAY = "alipay";

    /**
     * 微信支付
     */
    String METHOD_WECHAT = "wechat";

    // endregion
}