package com.huang.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.huang.common.ErrorCode;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.ticket.TicketQueryRequest;
import com.huang.model.entity.*;


import com.huang.mapper.TicketMapper;
import com.huang.model.vo.ReservationsVO;
import com.huang.model.vo.ScenicVO;
import com.huang.model.vo.TicketVO;
import com.huang.service.ScenicService;
import com.huang.service.TicketService;
import com.huang.service.UserService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 帖子服务实现
 *
 *
 *
 */
@Service
@Slf4j
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {

    @Resource
    private ScenicService scenicService;

    @Override
    public void validTicket(Ticket ticket, boolean add) {
        if (ticket == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }

    /**
     * 获取查询包装类
     *
     * @param ticketQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<Ticket> getQueryWrapper(TicketQueryRequest ticketQueryRequest) {
        QueryWrapper<Ticket> queryWrapper = new QueryWrapper<>();
        if (ticketQueryRequest == null) {
            return queryWrapper;
        }
//        String searchText = ticketQueryRequest.getSearchText();
//        String sortField = postQueryRequest.getSortField();
//        String sortOrder = ticketQueryRequest.getSortOrder();
//        Long id = ticketQueryRequest.getId();
//        String title = postQueryRequest.getTitle();
//        String content = postQueryRequest.getContent();
//        List<String> tagList = postQueryRequest.getTags();
//        Long userId = postQueryRequest.getUserId();
//        Long notId = postQueryRequest.getNotId();
        // 拼接查询条件
//        if (StringUtils.isNotBlank(searchText)) {
//            queryWrapper.and(qw -> qw.like("title", searchText).or().like("content", searchText));
//        }
//        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
//        queryWrapper.like(StringUtils.isNotBlank(content), "content", content);
//        if (CollUtil.isNotEmpty(tagList)) {
//            for (String tag : tagList) {
//                queryWrapper.like("tags", "\"" + tag + "\"");
//            }
//        }
//        queryWrapper.ne(ObjectUtils.isNotEmpty(notId), "id", notId);
//        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
//        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
//        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
//                sortField);
        return queryWrapper;
    }

    @Override
    public Page<TicketVO> getTicketVOPage(Page<Ticket> ticketPage) {
        List<Ticket> ticketList = ticketPage.getRecords();
        Page<TicketVO> ticketVOPage = new Page<>(ticketPage.getCurrent(), ticketPage.getSize(), ticketPage.getTotal());
        if (CollUtil.isEmpty(ticketList)) {
            return ticketVOPage;
        }

        // 1. 关联查询用户信息
        Set<Long> scenicIdSet = ticketList.stream().map(Ticket::getScenicId).collect(Collectors.toSet());
        Map<Long, List<Scenic>> scenicIdScenicListMap = scenicService.listByIds(scenicIdSet).stream()
                .collect(Collectors.groupingBy(Scenic::getId));

        // 填充信息
        List<TicketVO> reservationsVOList = ticketList.stream().map(ticket -> {
            TicketVO ticketVO = new TicketVO();
            BeanUtils.copyProperties(ticket, ticketVO);
            long scenicId = ticket.getScenicId();
            Scenic scenic = null;
            if (scenicIdScenicListMap.containsKey(scenicId)) {
                scenic = scenicIdScenicListMap.get(scenicId).get(0);
            }
            ticketVO.setScenicVO(ScenicVO.objToVo(scenic));
            return ticketVO;
        }).collect(Collectors.toList());
        ticketVOPage.setRecords(reservationsVOList);
        return ticketVOPage;
    }

    @Override
    public TicketVO getTicketVO(Ticket ticket) {
        TicketVO ticketVO = new TicketVO();
        BeanUtils.copyProperties(ticket, ticketVO);
        ticketVO.setScenicVO(ScenicVO.objToVo(scenicService.getById(ticket.getScenicId())));
        return ticketVO;
    }
}




