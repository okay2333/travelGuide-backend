package com.huang.constant;

/**
 * 订单常量
 *
 *
 *
 */
public interface OrdersConstant {

    /**
     * 订单状态键
     */
    String ORDER_STATUS_KEY = "order_status";

    // region 订单状态

    /**
     * 待支付
     */
    Integer STATUS_PENDING = 0;

    /**
     * 已支付
     */
    Integer STATUS_PAID = 1;

    /**
     * 已取消
     */
    Integer STATUS_CANCELLED = 2;

    // endregion

    // region 订单类型

    /**
     * 普通订单
     */
    String TYPE_NORMAL = "normal";

    /**
     * 优惠订单
     */
    String TYPE_DISCOUNT = "discount";

    // endregion
}