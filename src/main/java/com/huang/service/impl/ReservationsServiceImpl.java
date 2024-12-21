package com.huang.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.huang.common.ErrorCode;
import com.huang.exception.BusinessException;
import com.huang.mapper.ReservationsMapper;
import com.huang.model.dto.reservations.ReservationsQueryRequest;
import com.huang.model.entity.*;
import com.huang.model.vo.PostVO;
import com.huang.model.vo.ReservationsVO;
import com.huang.model.vo.ScenicVO;
import com.huang.service.ReservationsService;
import com.huang.service.ScenicService;
import com.huang.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author okay
* @description 针对表【reservations(预约表)】的数据库操作Service实现
* @createDate 2024-09-04 21:15:02
*/
@Service
public class ReservationsServiceImpl extends ServiceImpl<ReservationsMapper, Reservations>
    implements ReservationsService {

    @Resource
    private ReservationsMapper reservationsMapper;
    @Resource
    @Lazy
    private ScenicService scenicService;
    @Override
    public void validReservations(Reservations reservations, boolean add) {

    }

    @Override
    public QueryWrapper<Reservations> getQueryWrapper(ReservationsQueryRequest reservationsQueryRequest) {
        QueryWrapper<Reservations> queryWrapper = new QueryWrapper<>();
        if (reservationsQueryRequest == null) {
            return queryWrapper;
        }
        String searchText = reservationsQueryRequest.getSearchText();
        Long id = reservationsQueryRequest.getId();
        Date openDateTime = reservationsQueryRequest.getOpenDateTime();
        // 使用hutool工具类格式化日期
        String formattedDate = DateUtil.format(openDateTime, "yyyy-MM-dd");
        Long stock = reservationsQueryRequest.getStock();

        Long scenicId = reservationsQueryRequest.getScenicId();
        // 拼接查询条件
        if (StringUtils.isNotBlank(searchText)) {
            queryWrapper.and(qw -> qw.like("openDateTime", searchText).or().like("stock", searchText));
        }
        if (openDateTime != null) {
            queryWrapper.like("openDateTime", formattedDate);
        }
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(scenicId), "scenicId", scenicId);
        return queryWrapper;
    }

    @Override
    public ReservationsVO getReservationsVO(Reservations reservations) {
        if (reservations == null) {
            return null;
        }
        ReservationsVO reservationsVO = new ReservationsVO();
        BeanUtils.copyProperties(reservations, reservationsVO);
        long scenicId = reservations.getScenicId();
        Scenic scenic = scenicService.getById(scenicId);
        if (scenic != null) {
            reservationsVO.setScenicVO(ScenicVO.objToVo(scenic));
        }
        return reservationsVO;
    }

    @Override
    public List<String> listOpenDateTime() {
        // 查询数据库reservations表中的open_date_time字段
        QueryWrapper<Reservations> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("openDateTime");
        List<Object> results = reservationsMapper.selectObjs(queryWrapper);
        return results == null ? null : (List<String>) (List<?>) results;
    }

    @Override
    public Page<ReservationsVO> getReservationsVOPage(Page<Reservations> reservationsPage) {
        List<Reservations> reservationsList = reservationsPage.getRecords();
        Page<ReservationsVO> reservationsVOPage = new Page<>(reservationsPage.getCurrent(), reservationsPage.getSize(), reservationsPage.getTotal());
        if (CollUtil.isEmpty(reservationsList)) {
            return reservationsVOPage;
        }
        // 1. 关联查询用户信息
        Set<Long> scenicIdSet = reservationsList.stream().map(Reservations::getScenicId).collect(Collectors.toSet());
        Map<Long, List<Scenic>> scenicIdScenicListMap = scenicService.listByIds(scenicIdSet).stream()
                .collect(Collectors.groupingBy(Scenic::getId));

        // 填充信息
        List<ReservationsVO> reservationsVOList = reservationsList.stream().map(reservations -> {
            ReservationsVO reservationsVO = new ReservationsVO();
            BeanUtils.copyProperties(reservations, reservationsVO);
            long scenicId = reservations.getScenicId();
            Scenic scenic = null;
            if (scenicIdScenicListMap.containsKey(scenicId)) {
                scenic = scenicIdScenicListMap.get(scenicId).get(0);
            }
            reservationsVO.setScenicVO(ScenicVO.objToVo(scenic));
            return reservationsVO;
        }).collect(Collectors.toList());
        reservationsVOPage.setRecords(reservationsVOList);
        return reservationsVOPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateReservationsCount(String operation, Long reservationsId,int count) {
        if (StringUtils.isBlank(operation) || reservationsId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 预约操作减库存
        if ("add".equals(operation)) {
            //1. 检查库存是否存在
            Reservations reservations = this.getById(reservationsId);
            if (reservations == null) {
                throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
            }
            //2. 检查商品库存是否足够
            if (reservations.getStock() < count) {
                throw new BusinessException(ErrorCode.STOCK_NOT_ENOUGH_ERROR);
            }
            //3. 扣减库存
            reservations.setStock(reservations.getStock() - count);
            this.updateById(reservations);
        }
//        取消预约操作加库存
        if ("delete".equals(operation) ) {
            //1. 检查库存是否存在
            Reservations reservations = this.getById(reservationsId);
            if (reservations == null) {
                throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
            }
            //2. 加库存
            reservations.setStock(reservations.getStock() + count);
            this.updateById(reservations);
        }

    }
}




