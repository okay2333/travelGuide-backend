package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.reservationsTime.ReservationsTimeQueryRequest;
import com.huang.model.entity.ReservationsTime;

/**
* @author okay
* @description 针对表【reservations_time(预约时间表)】的数据库操作Service
* @createDate 2024-09-04 21:15:02
*/
public interface ReservationsTimeService extends IService<ReservationsTime> {

    QueryWrapper<ReservationsTime> getQueryWrapper(ReservationsTimeQueryRequest reservationsTimeQueryRequest);
}
