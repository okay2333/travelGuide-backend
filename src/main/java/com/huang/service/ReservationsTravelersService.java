package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.reservations.ReservationsQueryRequest;
import com.huang.model.dto.reservationsTravelers.ReservationsTravelersAddRequest;
import com.huang.model.entity.ReservationsTravelers;
import com.huang.model.vo.ReservationsTravelersVO;

import java.util.List;

/**
* @author okay
* @description 针对表【reservations_time(预约时间表)】的数据库操作Service
* @createDate 2024-09-04 21:15:02
*/
public interface ReservationsTravelersService extends IService<ReservationsTravelers> {




    List<ReservationsTravelersVO> byUserId();

    /**
     * 统计用户的预约数量（去重reservationId）
     * @param userId 用户ID
     * @return 预约数量
     */
     long countUserReservations(Long userId);

    void validAddRequest(ReservationsTravelersAddRequest reservationsTravelersAddRequest);

    boolean deleteByReservationsId(long id);

    List<ReservationsTravelersVO> byAllUserId(Long reservationsId);
}
