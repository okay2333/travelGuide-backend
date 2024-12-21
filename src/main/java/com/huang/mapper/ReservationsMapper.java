package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.model.entity.Reservations;
import org.apache.ibatis.annotations.Select;


/**
 * 帖子数据库操作
 *
 * 
 * 
 */
public interface ReservationsMapper extends BaseMapper<Reservations> {

    @Select("SELECT COUNT(DISTINCT reservationsId) FROM reservations_travelers WHERE userId = #{userId}")
    long countUserReservations(Long userId);
}




