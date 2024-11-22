package com.huang.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.huang.common.ErrorCode;
import com.huang.exception.BusinessException;
import com.huang.model.dto.ticketScenic.TicketScenicQueryRequest;
import com.huang.model.entity.*;


import com.huang.mapper.TicketScenicMapper;
import com.huang.model.vo.TicketScenicVO;
import com.huang.model.vo.UserVO;
import com.huang.service.TicketScenicService;
import com.huang.service.UserService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class TicketScenicServiceImpl extends ServiceImpl<TicketScenicMapper, TicketScenic> implements TicketScenicService {

    @Resource
    private UserService userService;

    /**
     * 获取查询包装类
     *
     * @param ticketScenicQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<TicketScenic> getQueryWrapper(TicketScenicQueryRequest ticketScenicQueryRequest) {
        QueryWrapper<TicketScenic> queryWrapper = new QueryWrapper<>();
        if (ticketScenicQueryRequest == null) {
            return queryWrapper;
        }
//        String searchText = ticketScenicQueryRequest.getSearchText();
//        String sortField = postQueryRequest.getSortField();
//        String sortOrder = ticketScenicQueryRequest.getSortOrder();
//        Long id = ticketScenicQueryRequest.getId();
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
    public Page<TicketScenicVO> getTicketScenicVOPage(Page<TicketScenic> ticketScenicPage) {
        List<TicketScenic> ticketScenicList = ticketScenicPage.getRecords();
        Page<TicketScenicVO> ticketScenicVOPage = new Page<>(ticketScenicPage.getCurrent(), ticketScenicPage.getSize(), ticketScenicPage.getTotal());
        if (CollUtil.isEmpty(ticketScenicList)) {
            return ticketScenicVOPage;
        }

        // 填充信息
        List<TicketScenicVO> ticketScenicVOList = ticketScenicList.stream().map(ticketScenic -> {
            TicketScenicVO ticketScenicVO = TicketScenicVO.objToVo(ticketScenic);
            return ticketScenicVO;
        }).collect(Collectors.toList());
        ticketScenicVOPage.setRecords(ticketScenicVOList);
        return ticketScenicVOPage;
    }

    @Override
    public TicketScenicVO getTicketScenicVO(TicketScenic ticketScenic) {
        TicketScenicVO ticketScenicVO = TicketScenicVO.objToVo(ticketScenic);
        return ticketScenicVO;
    }
}




