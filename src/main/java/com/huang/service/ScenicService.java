package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.post.PostQueryRequest;
import com.huang.model.dto.scenic.ScenicQueryRequest;
import com.huang.model.entity.Post;
import com.huang.model.entity.Scenic;
import com.huang.model.vo.PostVO;
import com.huang.model.vo.ScenicVO;

/**
 * 景区管理
 *
 * 
 * 
 */
public interface ScenicService extends IService<Scenic> {

    /**
     * 校验
     *
     * @param scenic
     * @param add
     */
    void validScenic(Scenic scenic, boolean add);

    /**
     * 获取查询条件
     *
     * @param scenicQueryRequest
     * @return
     */
    QueryWrapper<Scenic> getQueryWrapper(ScenicQueryRequest scenicQueryRequest);

    /**
     * 分页获取景区封装
     *
     * @param scenicPage
     * @return
     */
    Page<ScenicVO> getScenicVOPage(Page<Scenic> scenicPage);

    /**
     * 获取景区封装
     *
     * @param post
     * @return
     */
//    PostVO getScenicVO(Post post);
    /**
     * 从 ES 查询
     *
     * @param postQueryRequest
     * @return
     */
    Page<Post> searchFromEs(PostQueryRequest postQueryRequest);



    /**
    * @author okay
    * @description 针对表【reservations_time(预约时间表)】的数据库操作Service
    * @createDate 2024-09-04 21:15:02
    */

}
