package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.annotation.AuthCheck;
import com.huang.common.BaseResponse;
import com.huang.common.DeleteRequest;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.constant.UserConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.post.PostAddRequest;
import com.huang.model.dto.post.PostEditRequest;
import com.huang.model.dto.post.PostQueryRequest;
import com.huang.model.dto.post.PostUpdateRequest;
import com.huang.model.entity.Post;
import com.huang.model.entity.Qrcodeverification;
import com.huang.model.entity.User;
import com.huang.model.vo.PostVO;
import com.huang.service.PostService;
import com.huang.service.QrcodeverificationService;
import com.huang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 帖子接口
 *
 * 
 * 
 */
@RestController
@RequestMapping("/qrcodeverification")
@Slf4j
public class QrcodeverificationController {

    @Resource
    private QrcodeverificationService qrcodeverificationService;

    @Resource
    private UserService userService;



    // region 增删改查

    /**
     * 创建
     *
     * @param qrcodeverificationAddRequest
     * @return
     */
//    @PostMapping("/add")
//    public BaseResponse<Long> addQrcodeverification(@RequestBody QrcodeverificationAddRequest qrcodeverificationAddRequest) {
//        if (qrcodeverificationAddRequest == null) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 先判断是否已登录
//        if (!StpUtil.isLogin()) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
//        }
//        Post post = new Post();
//        BeanUtils.copyProperties(postAddRequest, post);
//        List<String> tags = postAddRequest.getTags();
//        List<String> covers = postAddRequest.getCovers();
//        if (tags != null) {
//            post.setTags(JSONUtil.toJsonStr(tags));
//        }
//        if (covers != null) {
//            post.setCovers(JSONUtil.toJsonStr(covers));
//        }
//        postService.validPost(post, true);
//        User loginUser = userService.getLoginUser(StpUtil.getLoginIdAsLong());
//        post.setUserId(loginUser.getId());
//        post.setFavourNum(0);
//        post.setThumbNum(0);
//        boolean result = postService.save(post);
//        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
//        long newPostId = post.getId();
//        return ResultUtils.success(newPostId);
//    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @return
     */
//    @PostMapping("/delete")
//    public BaseResponse<Boolean> deletePost(@RequestBody DeleteRequest deleteRequest) {
//        if (deleteRequest == null || deleteRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 先判断是否已登录
//        if (!StpUtil.isLogin()) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
//        }
//        long id = deleteRequest.getId();
//        // 判断是否存在
//        Post oldPost = postService.getById(id);
////        TODO 改
//        ThrowUtils.throwIf(oldPost == null, ErrorCode.NOT_FOUND_ERROR);
//        // 仅本人或管理员可删除
//        if (!oldPost.getUserId().equals(StpUtil.getLoginIdAsLong()) && !userService.isAdmin(StpUtil.getLoginIdAsLong())) {
//            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//        }
//        boolean b = postService.removeById(id);
//        return ResultUtils.success(b);
//    }

    /**
     * 更新（仅管理员）
     *
     * @param postUpdateRequest
     * @return
     */
//    @PostMapping("/update")
//    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
//    public BaseResponse<Boolean> updatePost(@RequestBody PostUpdateRequest postUpdateRequest) {
//        if (postUpdateRequest == null || postUpdateRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        Post post = new Post();
//        BeanUtils.copyProperties(postUpdateRequest, post);
//        List<String> tags = postUpdateRequest.getTags();
//        if (tags != null) {
//            post.setTags(JSONUtil.toJsonStr(tags));
//        }
//        // 参数校验
//        postService.validPost(post, false);
//        long id = postUpdateRequest.getId();
//        // 判断是否存在
//        Post oldPost = postService.getById(id);
//        ThrowUtils.throwIf(oldPost == null, ErrorCode.NOT_FOUND_ERROR);
//        boolean result = postService.updateById(post);
//        return ResultUtils.success(result);
//    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<Qrcodeverification> getQrcodeverificationVOById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Qrcodeverification qrcodeverification = qrcodeverificationService.getById(id);
        if (qrcodeverification == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(qrcodeverification);
    }

    /**
     * 根据 id 获取
     *
     * @param orderId
     * @return
     */
    @GetMapping("/get/vo/byorderId")
    public BaseResponse<Qrcodeverification> getQrcodeverificationVOByOrderId(String orderId) {
        System.out.println("orderId:"+orderId);
        QueryWrapper<Qrcodeverification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderId", orderId);
        Qrcodeverification qrcodeverification = qrcodeverificationService.getOne(queryWrapper);
        if (qrcodeverification == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(qrcodeverification);
    }

//    /**
//     * 分页获取列表（仅管理员）
//     *
//     * @param postQueryRequest
//     * @return
//     */
//    @PostMapping("/list/page")
//    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
//    public BaseResponse<Page<Post>> listPostByPage(@RequestBody PostQueryRequest postQueryRequest) {
//        long current = postQueryRequest.getCurrent();
//        long size = postQueryRequest.getPageSize();
//        Page<Post> postPage = postService.page(new Page<>(current, size),
//                postService.getQueryWrapper(postQueryRequest));
//        return ResultUtils.success(postPage);
//    }

    /**
     * 分页获取列表（封装类）
     *
     * @param postQueryRequest
     * @return
     */
//    @PostMapping("/list/page/vo")
//    public BaseResponse<Page<Qrcodeverification>> listPostVOByPage(@RequestBody QrcodeverificationRequest qrcodeverificationRequest) {
//        long current = postQueryRequest.getCurrent();
//        long size = postQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<Post> postPage = postService.page(new Page<>(current, size),
//                postService.getQueryWrapper(postQueryRequest));
//        return ResultUtils.success(postService.getPostVOPage(postPage));
//    }

    /**
     * 分页获取当前用户创建的资源列表
     *
     * @param postQueryRequest
     * @return
     */
//    @PostMapping("/my/list/page/vo")
//    public BaseResponse<Page<PostVO>> listMyPostVOByPage(@RequestBody PostQueryRequest postQueryRequest) {
//        if (postQueryRequest == null) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 先判断是否已登录
//        if (!StpUtil.isLogin()) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
//        }
//        User loginUser = userService.getLoginUser(StpUtil.getLoginIdAsLong());
//        postQueryRequest.setUserId(loginUser.getId());
//        long current = postQueryRequest.getCurrent();
//        long size = postQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<Post> postPage = postService.page(new Page<>(current, size),
//                postService.getQueryWrapper(postQueryRequest));
//        // TODO 点赞业务有问题
//        return ResultUtils.success(postService.getPostVOPage(postPage));
//    }

    // endregion

    /**
     * 分页搜索（从 ES 查询，封装类）
     *
     * @param postQueryRequest
     * @param request
     * @return
     */
//    @PostMapping("/search/page/vo")
//    public BaseResponse<Page<PostVO>> searchPostVOByPage(@RequestBody PostQueryRequest postQueryRequest,
//            HttpServletRequest request) {
//        long size = postQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
//        return ResultUtils.success(postService.getPostVOPage(postPage));
//    }

    /**
     * 编辑（用户）
     *
     * @param postEditRequest
     * @return
     */
//    @PostMapping("/edit")
//    public BaseResponse<Boolean> editPost(@RequestBody PostEditRequest postEditRequest) {
//        if (postEditRequest == null || postEditRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 先判断是否已登录
//        if (!StpUtil.isLogin()) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
//        }
//        Post post = new Post();
//        BeanUtils.copyProperties(postEditRequest, post);
//        List<String> tags = postEditRequest.getTags();
//        if (tags != null) {
//            post.setTags(JSONUtil.toJsonStr(tags));
//        }
//        // 参数校验
//        postService.validPost(post, false);
//        long id = postEditRequest.getId();
//        // 判断是否存在
//        Post oldPost = postService.getById(id);
//        ThrowUtils.throwIf(oldPost == null, ErrorCode.NOT_FOUND_ERROR);
//        // 仅本人或管理员可编辑
//        // todo 验证用户权限
//        if (!oldPost.getUserId().equals(StpUtil.getLoginIdAsLong()) && !userService.isAdmin(StpUtil.getLoginIdAsLong())) {
//            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//        }
//        boolean result = postService.updateById(post);
//        return ResultUtils.success(result);
//    }

}
