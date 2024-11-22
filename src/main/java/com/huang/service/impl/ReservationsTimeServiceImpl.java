package com.huang.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.constant.CommonConstant;
import com.huang.mapper.ReservationsMapper;
import com.huang.mapper.ReservationsTimeMapper;
import com.huang.model.dto.post.PostQueryRequest;
import com.huang.model.dto.reservations.ReservationsQueryRequest;
import com.huang.model.dto.reservationsTime.ReservationsTimeQueryRequest;
import com.huang.model.entity.Post;
import com.huang.model.entity.Reservations;
import com.huang.model.entity.ReservationsTime;
import com.huang.model.vo.ReservationsVO;
import com.huang.service.ReservationsService;
import com.huang.service.ReservationsTimeService;
import com.huang.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author okay
* @description 针对表【reservations(预约时间表)】的数据库操作Service实现
* @createDate 2024-09-04 21:15:02
*/
@Service
public class ReservationsTimeServiceImpl extends ServiceImpl<ReservationsTimeMapper, ReservationsTime>
    implements ReservationsTimeService {


    /**
     * 获取查询包装类
     *
     * @param reservationsTimeQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<ReservationsTime> getQueryWrapper(ReservationsTimeQueryRequest reservationsTimeQueryRequest) {
        QueryWrapper<ReservationsTime> queryWrapper = new QueryWrapper<>();
        if (reservationsTimeQueryRequest == null) {
            return queryWrapper;
        }
        String sortField = reservationsTimeQueryRequest.getSortField();
        String sortOrder = reservationsTimeQueryRequest.getSortOrder();
        Long id = reservationsTimeQueryRequest.getId();
        Long reservationsId = reservationsTimeQueryRequest.getReservationsId();


        queryWrapper.eq(ObjectUtils.isNotEmpty(reservationsId), "reservationsId", reservationsId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




