package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.ticketScenic.TicketScenicQueryRequest;
import com.huang.model.entity.Reservations;
import com.huang.model.entity.TicketScenic;
import com.huang.model.vo.ReservationsVO;
import com.huang.model.vo.TicketScenicVO;

/**
 * 帖子服务
 *
 *
 *
 */
public interface TicketScenicService extends IService<TicketScenic> {

//    /**
//     * 校验
//     *
//     * @param reservations
//     * @param add
//     */
//    void validReservations(Reservations reservations, boolean add);

    /**
     * 获取查询条件
     *
     * @param ticketScenicQueryRequest
     * @return
     */
    QueryWrapper<TicketScenic> getQueryWrapper(TicketScenicQueryRequest ticketScenicQueryRequest);

    Page<TicketScenicVO> getTicketScenicVOPage(Page<TicketScenic> ticketScenicPage);

    TicketScenicVO getTicketScenicVO(TicketScenic ticketScenic);


//    /**
//     * 获取帖子封装
//     *
//     * @param reservations
//     * @return
//     */
//    ReservationsVO getReservationsVO(Reservations reservations);


}
