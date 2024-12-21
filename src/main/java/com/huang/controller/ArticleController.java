package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.annotation.AuthCheck;
import com.huang.common.BaseResponse;
import com.huang.common.DeleteRequest;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.constant.UserConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.article.ArticleAddRequest;
import com.huang.model.dto.article.ArticleQueryRequest;
import com.huang.model.dto.article.ArticleUpdateRequest;
import com.huang.model.entity.Article;
import com.huang.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 攻略接口
 *
 * 
 * 
 */
@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    @Resource
    private ArticleService articleService;


    // region 增删改查

    /**
     * 创建（仅管理员）
     *
     * @param articleAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addArticle(@RequestBody ArticleAddRequest articleAddRequest) {
        if (articleAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Article article = new Article();
        BeanUtils.copyProperties(articleAddRequest, article);

        articleService.validArticle(article, true);
        boolean result = articleService.save(article);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newArticleId = article.getId();
        return ResultUtils.success(newArticleId);
    }

    /**
     * 删除（仅管理员）
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteArticle(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        long id = deleteRequest.getId();
        // 判断是否存在
        Article oldArticle = articleService.getById(id);
        ThrowUtils.throwIf(oldArticle == null, ErrorCode.NOT_FOUND_ERROR);
        boolean b = articleService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param articleUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateArticle(@RequestBody ArticleUpdateRequest articleUpdateRequest) {
        if (articleUpdateRequest == null || articleUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Article article = new Article();
        BeanUtils.copyProperties(articleUpdateRequest, article);
        // 参数校验
        articleService.validArticle(article, false);
        long id = articleUpdateRequest.getId();
        // 判断是否存在
        Article oldArticle = articleService.getById(id);
        ThrowUtils.throwIf(oldArticle == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = articleService.updateById(article);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<Article> getArticleVOById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Article article = articleService.getById(id);
        if (article == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(article);
    }

    /**
     * 分页获取列表（仅管理员）
     *
     * @param articleQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<Article>> listArticleByPage(@RequestBody ArticleQueryRequest articleQueryRequest) {
        long current = articleQueryRequest.getCurrent();
        long size = articleQueryRequest.getPageSize();
        Page<Article> articlePage = articleService.page(new Page<>(current, size),
                articleService.getQueryWrapper(articleQueryRequest));
        return ResultUtils.success(articlePage);
    }






}
