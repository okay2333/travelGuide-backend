package com.huang.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.ErrorCode;
import com.huang.constant.CommonConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.mapper.ArticleMapper;
import com.huang.mapper.PostFavourMapper;
import com.huang.model.dto.article.ArticleQueryRequest;
import com.huang.model.dto.post.PostEsDTO;
import com.huang.model.dto.post.PostQueryRequest;
import com.huang.model.entity.*;
import com.huang.model.vo.PostVO;
import com.huang.model.vo.UserVO;
import com.huang.service.ArticleService;
import com.huang.service.PostService;
import com.huang.service.UserService;
import com.huang.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 帖子服务实现
 *
 * 
 * 
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {



    @Override
    public void validArticle(Article article, boolean add) {
        if (article == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String title = article.getTitle();
        String content = article.getContent();
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(title, content), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(title) && title.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长");
        }
        if (StringUtils.isNotBlank(content) && content.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
    }

    /**
     * 获取查询包装类
     *
     * @param articleQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<Article> getQueryWrapper(ArticleQueryRequest articleQueryRequest) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (articleQueryRequest == null) {
            return queryWrapper;
        }
        return queryWrapper;
    }


}




