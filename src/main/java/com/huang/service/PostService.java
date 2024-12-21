package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.post.PostQueryRequest;
import com.huang.model.entity.Post;
import com.huang.model.vo.PostVO;

/**
 * 帖子服务
 *
 * 
 * 
 */
public interface PostService extends IService<Post> {

    /**
     * 校验
     *
     * @param post
     * @param add
     */
    void validPost(Post post, boolean add);

    /**
     * 获取查询条件
     *
     * @param postQueryRequest
     * @return
     */
    QueryWrapper<Post> getQueryWrapper(PostQueryRequest postQueryRequest);

    /**
     * 从 ES 查询
     *
     * @param postQueryRequest
     * @return
     */
    Page<Post> searchFromEs(PostQueryRequest postQueryRequest);

    /**
     * 获取帖子封装
     *
     * @param post
     * @return
     */
    PostVO getPostVO(Post post);

    /**
     * 分页获取帖子封装
     *
     * @param postPage
     * @return
     */
    Page<PostVO> getPostVOPage(Page<Post> postPage);



    long countMyFavourPost(Long userId);
}
