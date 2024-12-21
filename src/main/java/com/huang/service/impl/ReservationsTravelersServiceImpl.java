package com.huang.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.exception.BusinessException;
import com.huang.mapper.ReservationsMapper;
import com.huang.mapper.ReservationsTravelersMapper;
import com.huang.mapper.ScenicMapper;
import com.huang.model.dto.reservations.ReservationsQueryRequest;
import com.huang.model.dto.reservationsTime.Traveler;
import com.huang.model.dto.reservationsTravelers.ReservationsTravelersAddRequest;
import com.huang.model.entity.Reservations;
import com.huang.model.entity.ReservationsTravelers;
import com.huang.model.entity.Scenic;
import com.huang.model.vo.ReservationsTravelersVO;
import com.huang.model.vo.ReservationsVO;
import com.huang.model.vo.ScenicVO;
import com.huang.service.ReservationsService;
import com.huang.service.ReservationsTravelersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author okay
* @description 针对表【reservations(预约时间表)】的数据库操作Service实现
* @createDate 2024-09-04 21:15:02
*/
@Service
public class ReservationsTravelersServiceImpl extends ServiceImpl<ReservationsTravelersMapper, ReservationsTravelers>
    implements ReservationsTravelersService {

    @Resource
    private ReservationsTravelersMapper reservationsTravelersMapper;

    @Resource
    private ReservationsMapper reservationsMapper;

    @Resource
    private ReservationsService reservationsService;
    @Resource
    private ScenicMapper scenicMapper;
    @Override
    public List<ReservationsTravelersVO> byUserId() {
        QueryWrapper<ReservationsTravelers> queryWrapper = new QueryWrapper<>();
        String uid = String.valueOf(StpUtil.getLoginIdAsLong());
        queryWrapper.eq("userId", uid);
        List<ReservationsTravelers> results = reservationsTravelersMapper.selectList(queryWrapper);

        if (results == null || results.isEmpty()) {
            return null;
        }

        Map<Long, ReservationsTravelersVO> reservationsMap = new HashMap<>();
        for (ReservationsTravelers rt : results) {
            ReservationsTravelersVO vo = reservationsMap.get(rt.getReservationsId());
            if (vo == null) {
                vo = new ReservationsTravelersVO();
                vo.setId(rt.getId());
                vo.setUserId(rt.getUserId());
                vo.setReservationsId(rt.getReservationsId());
                vo.setCreateTime(rt.getCreateTime());
                vo.setUpdateTime(rt.getUpdateTime());
                vo.setTravelerList(new ArrayList<>());

                // Fetch Reservations object
                Reservations reservations = reservationsMapper.selectById(rt.getReservationsId());
                if (reservations != null) {
                    ReservationsVO reservationsVO = new ReservationsVO();
                    reservationsVO.setId(reservations.getId());
                    reservationsVO.setScenicId(reservations.getScenicId());
                    reservationsVO.setStock(reservations.getStock());
                    reservationsVO.setOpenDateTime(reservations.getOpenDateTime());
                    reservationsVO.setCreateTime(reservations.getCreateTime());
                    reservationsVO.setUpdateTime(reservations.getUpdateTime());

                    // Fetch Scenic object
                    Scenic scenic = scenicMapper.selectById(reservations.getScenicId());
                    if (scenic != null) {
                        ScenicVO scenicVO = new ScenicVO();
                        scenicVO.setId(scenic.getId());
                        scenicVO.setAddress(scenic.getAddress());
                        scenicVO.setScenicName(scenic.getScenicName());
                        // Set other properties of ScenicVO as needed
                        reservationsVO.setScenicVO(scenicVO);
                    }

                    vo.setReservationsVO(reservationsVO);
                }

                reservationsMap.put(rt.getReservationsId(), vo);
            }
            Traveler traveler = new Traveler();
            traveler.setFullName(rt.getFullName());
            traveler.setIdNumber(rt.getIdNumber());
            traveler.setPhoneNumber(rt.getPhoneNumber());
            vo.getTravelerList().add(traveler);
        }

        return new ArrayList<>(reservationsMap.values());
    }

    @Override
    public long countUserReservations(Long userId) {
        return reservationsMapper.countUserReservations(userId);
    }

    @Override
    public void validAddRequest(ReservationsTravelersAddRequest reservationsTravelersAddRequest) {
        QueryWrapper<ReservationsTravelers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", StpUtil.getLoginIdAsLong()).eq("reservationsId", reservationsTravelersAddRequest.getReservationsId());
        Long count = reservationsTravelersMapper.selectCount(queryWrapper);
//       已经已经预约过抛出异常
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }

    @Override
    public boolean deleteByReservationsId(long reservationsId) {
        QueryWrapper<ReservationsTravelers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reservationsId", reservationsId);
        long count = this.count(queryWrapper);
        reservationsService.updateReservationsCount("delete", reservationsId, (int) count);
        return this.remove(queryWrapper);
    }
}




