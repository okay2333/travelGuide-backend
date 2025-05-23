package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.BaseResponse;
import com.huang.common.DeleteRequest;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.constant.UserConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.scenic.ScenicAddRequest;
import com.huang.model.dto.scenic.ScenicQueryRequest;
import com.huang.model.dto.scenic.ScenicUpdateRequest;
import com.huang.model.entity.CountSource;
import com.huang.model.entity.CountTicket;
import com.huang.model.entity.CountTrend;
import com.huang.model.entity.Scenic;
import com.huang.model.vo.ScenicVO;
import com.huang.service.CountService;
import com.huang.service.ScenicService;
import com.huang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 景区管理接口
 *
 * 
 * 
 */
@RestController
@RequestMapping("/count")
@Slf4j
public class CountController {


    @Resource
    private CountService countService;


    // region 增删改查


    @GetMapping("/CountTrend")
    public BaseResponse<List<CountTrend>> getCountTrend() {
        List<CountTrend> countTrends = countService.getCountTrend();
        return ResultUtils.success(countTrends);
    }
    @GetMapping("/CountTicket")
    public BaseResponse<List<CountTicket>> getCountTicket() {
        List<CountTicket> countTickets = countService.CountTicket();
        return ResultUtils.success(countTickets);
    }

    @GetMapping("/CountSource")
    public BaseResponse<List<CountSource>> getCountSource() {
        List<CountSource> countTickets = countService.CountSource();
        return ResultUtils.success(countTickets);
    }


}
