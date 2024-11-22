package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.reservations.ReservationsQueryRequest;
import com.huang.model.entity.Reservations;
import com.huang.model.vo.ReservationsVO;

/**
 * 帖子服务
 *
 *
 *
 */
public interface ReservationsService extends IService<Reservations> {

    /**
     * 校验
     *
     * @param reservations
     * @param add
     */
    void validReservations(Reservations reservations, boolean add);

    /**
     * 获取查询条件
     *
     * @param reservationsQueryRequest
     * @return
     */
    QueryWrapper<Reservations> getQueryWrapper(ReservationsQueryRequest reservationsQueryRequest);



    /**
     * 获取帖子封装
     *
     * @param reservations
     * @return
     */
    ReservationsVO getReservationsVO(Reservations reservations);


}
