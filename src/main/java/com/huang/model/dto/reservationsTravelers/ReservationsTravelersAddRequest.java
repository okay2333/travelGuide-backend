package com.huang.model.dto.reservationsTravelers;


import com.huang.model.dto.reservationsTime.Traveler;
import lombok.Data;


import java.util.List;
@Data
public class ReservationsTravelersAddRequest {

    /**
     * 预约时间表 id
     */
    private Long reservationsId;

    /**
     * 游客信息列表
     */
    private List<Traveler> travelerList;
}
