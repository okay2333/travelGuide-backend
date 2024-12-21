package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.model.entity.Orders;
import org.apache.ibatis.annotations.Select;

/**
 * 帖子数据库操作
 *
 * 
 * 
 */
public interface OrdersMapper extends BaseMapper<Orders> {


    @Select("SELECT COUNT(id) FROM orders WHERE userId = #{userId}")
    long countUserOrders(Long userId);
}




