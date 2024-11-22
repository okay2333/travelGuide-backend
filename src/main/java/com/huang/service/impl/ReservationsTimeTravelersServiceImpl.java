package com.huang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.constant.CommonConstant;
import com.huang.mapper.ReservationsTimeMapper;
import com.huang.mapper.ReservationsTimeTravelersMapper;
import com.huang.model.dto.reservationsTime.ReservationsTimeQueryRequest;
import com.huang.model.entity.ReservationsTime;
import com.huang.model.entity.ReservationsTimeTravelers;
import com.huang.service.ReservationsTimeService;
import com.huang.service.ReservationsTimeTravelersService;
import com.huang.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
* @author okay
* @description 针对表【reservations(预约时间表)】的数据库操作Service实现
* @createDate 2024-09-04 21:15:02
*/
@Service
public class ReservationsTimeTravelersServiceImpl extends ServiceImpl<ReservationsTimeTravelersMapper, ReservationsTimeTravelers>
    implements ReservationsTimeTravelersService {



}




