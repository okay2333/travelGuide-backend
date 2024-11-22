package com.huang.model.dto.reservationsTimeTravelers;


import com.huang.model.dto.reservationsTime.Traveler;
import lombok.Data;


import java.util.List;
@Data
public class ReservationsTimeTravelersAddRequest {


    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 预约时间表 id
     */
    private Long reservationsId;

    /**
     * 预约时间表 id
     */
    private Long reservationsTimeId;


    /**
     * 游客信息列表
     */
    private List<Traveler> travelerList;
}
