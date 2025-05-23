package com.huang.service.impl;


import com.huang.mapper.CountMapper;
import com.huang.model.entity.CountSource;
import com.huang.model.entity.CountTicket;
import com.huang.model.entity.CountTrend;
import com.huang.service.CountService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CountServiceImpl implements CountService{
    @Resource
    private CountMapper countMapper;
    @Override
    public List<CountTrend> getCountTrend() {
        return countMapper.getVisitTrend();
    }

    @Override
    public List<CountTicket> CountTicket() {
        return countMapper.getScenicTicketDistribution();
    }

    @Override
    public List<CountSource> CountSource() {
        return countMapper.getUserDistribution();
    }
}
