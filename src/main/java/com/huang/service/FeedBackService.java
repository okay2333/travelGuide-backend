package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.feedback.FeedBackQueryRequest;
import com.huang.model.dto.post.PostQueryRequest;
import com.huang.model.dto.scenic.ScenicQueryRequest;
import com.huang.model.entity.FeedBack;
import com.huang.model.entity.Post;
import com.huang.model.entity.Scenic;
import com.huang.model.vo.FeedBackVO;
import com.huang.model.vo.ScenicVO;

/**
 * 景区管理
 *
 * 
 * 
 */
public interface FeedBackService extends IService<FeedBack> {



    /**
     * 获取查询条件
     *
     * @param feedBackQueryRequest
     * @return
     */
    QueryWrapper<FeedBack> getQueryWrapper(FeedBackQueryRequest feedBackQueryRequest);

    /**
     * 分页获取封装
     *
     * @param feedbackPage
     * @return
     */
    Page<FeedBackVO>  getFeedBackVOPage(Page<FeedBack> feedbackPage);



    FeedBackVO getFeedBackVOById(long id);




    /**
    * @author okay
    * @description 针对表【reservations_time(预约时间表)】的数据库操作Service
    * @createDate 2024-09-04 21:15:02
    */

}
