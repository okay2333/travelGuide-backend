package com.huang.service;


import com.huang.model.entity.CountSource;
import com.huang.model.entity.CountTicket;
import com.huang.model.entity.CountTrend;

import java.util.List;

public interface CountService {

    List<CountTrend> getCountTrend();

    List<CountTicket> CountTicket();

    List<CountSource> CountSource();
}
