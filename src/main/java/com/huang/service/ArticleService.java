package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.dto.article.ArticleQueryRequest;
import com.huang.model.dto.post.PostQueryRequest;
import com.huang.model.entity.Article;
import com.huang.model.entity.Post;
import com.huang.model.vo.PostVO;

/**
 * 帖子服务
 *
 * 
 * 
 */
public interface ArticleService extends IService<Article> {

    /**
     * 校验
     *
     * @param article
     * @param add
     */
    void validArticle(Article article, boolean add);

    /**
     * 获取查询条件
     *
     * @param articleQueryRequest
     * @return
     */
    QueryWrapper<Article> getQueryWrapper(ArticleQueryRequest articleQueryRequest);



}
