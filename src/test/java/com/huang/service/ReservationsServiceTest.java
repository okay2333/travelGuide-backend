package com.huang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.model.entity.Reservations;
import com.huang.model.entity.Scenic;
import com.huang.model.vo.ReservationsVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 用户服务测试
 * 
 */
@SpringBootTest
public class ReservationsServiceTest {

    @Resource
    private ReservationsService reservationsService;

    @Test
    void getReservationsVOPageReturnsCorrectPage() {
        Page<Reservations> reservationsPage = new Page<>(1, 10);
        List<Reservations> reservationsList = Arrays.asList(new Reservations(), new Reservations());
        reservationsPage.setRecords(reservationsList);
        Page<Scenic> scenicPage = new Page<>();
        Page<ReservationsVO> result = reservationsService.getReservationsVOPage(reservationsPage);
        System.out.println(result);
    }



}
