package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.ticket.TicketQueryRequest;
import com.huang.model.entity.Ticket;
import com.huang.model.vo.TicketVO;

/**
 * 帖子服务
 *
 *
 *
 */
public interface TicketService extends IService<Ticket> {

    /**
     * 校验
     *
     * @param ticket
     * @param add
     */
    void validTicket(Ticket ticket, boolean add);

    /**
     * 获取查询条件
     *
     * @param ticketQueryRequest
     * @return
     */
    QueryWrapper<Ticket> getQueryWrapper(TicketQueryRequest ticketQueryRequest);

    Page<TicketVO> getTicketVOPage(Page<Ticket> ticketScenicPage);

    TicketVO getTicketVO(Ticket ticket);





}
