package com.huang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.huang.mapper.ReservationsMapper;
import com.huang.model.dto.reservations.ReservationsQueryRequest;
import com.huang.model.entity.Reservations;
import com.huang.model.vo.ReservationsVO;
import com.huang.service.ReservationsService;
import org.springframework.stereotype.Service;

/**
* @author okay
* @description 针对表【reservations(预约表)】的数据库操作Service实现
* @createDate 2024-09-04 21:15:02
*/
@Service
public class ReservationsServiceImpl extends ServiceImpl<ReservationsMapper, Reservations>
    implements ReservationsService {

    @Override
    public void validReservations(Reservations reservations, boolean add) {

    }

    @Override
    public QueryWrapper<Reservations> getQueryWrapper(ReservationsQueryRequest reservationsQueryRequest) {
        return null;
    }

    @Override
    public ReservationsVO getReservationsVO(Reservations reservations) {
        return null;
    }
}




