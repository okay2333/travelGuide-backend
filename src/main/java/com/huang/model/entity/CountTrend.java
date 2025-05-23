package com.huang.model.entity;

import lombok.Data;

@Data
public class CountTrend {
    private String date;           // 日期，格式为 'MM-DD'
    private int reservationCount;  // 预约人数
    private int purchaseCount;     // 购票人数
    private int actualVisits;      // 实际到访人数
}
