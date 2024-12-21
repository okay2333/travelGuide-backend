package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.reservations.ReservationsQueryRequest;
import com.huang.model.entity.Reservations;
import com.huang.model.vo.ReservationsVO;

import java.util.List;

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

    List<String> listOpenDateTime();

    Page<ReservationsVO> getReservationsVOPage(Page<Reservations> reservationsPage);


    void updateReservationsCount(String operation, Long reservationsId,int count);
}
