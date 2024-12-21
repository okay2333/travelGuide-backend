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
import com.huang.model.dto.comment.CommentAddRequest;
import com.huang.model.entity.Comment;
import com.huang.model.vo.CommentVO;
import com.huang.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 攻略接口
 *
 *
 *
 */
@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    @Resource
    private CommentService commentService;


    // 添加评论
    @PostMapping("/add")
    public BaseResponse<Long> addComment(@RequestBody CommentAddRequest commentAddRequest) {
        System.out.println("commentAddRequest: " + JSONUtil.toJsonStr(commentAddRequest));
        if (commentAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentAddRequest, comment);
        comment.setUserId(StpUtil.getLoginIdAsLong());
        commentService.validComment(comment, true);
        boolean result = commentService.save(comment);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newCommentId = comment.getId();
        return ResultUtils.success(newCommentId);
    }

    // 查询帖子下的评论
    @GetMapping("/list")
    public BaseResponse<List<CommentVO>> listCommentsByPostId(@RequestParam long postId) {
        if (postId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<CommentVO> comments = commentService.getCommentsWithReplies(postId);
        return ResultUtils.success(comments);
    }


    // region 增删改查

//    /**
//     * 创建（仅管理员）
//     *
//     * @param commentAddRequest
//     * @return
//     */
//    @PostMapping("/add")
//    public BaseResponse<Long> addComment(@RequestBody CommentAddRequest commentAddRequest) {
//        if (commentAddRequest == null) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 先判断是否已登录
//        if (!StpUtil.isLogin()) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
//        }
//        Comment comment = new Comment();
//        BeanUtils.copyProperties(commentAddRequest, comment);
//
//        commentService.validComment(comment, true);
//        boolean result = commentService.save(comment);
//        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
//        long newCommentId = comment.getId();
//        return ResultUtils.success(newCommentId);
//    }
//
//    /**
//     * 删除（仅管理员）
//     *
//     * @param deleteRequest
//     * @return
//     */
//    @PostMapping("/delete")
//    public BaseResponse<Boolean> deleteComment(@RequestBody DeleteRequest deleteRequest) {
//        if (deleteRequest == null || deleteRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 先判断是否已登录
//        if (!StpUtil.isLogin()) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
//        }
//        long id = deleteRequest.getId();
//        // 判断是否存在
//        Comment oldComment = commentService.getById(id);
//        ThrowUtils.throwIf(oldComment == null, ErrorCode.NOT_FOUND_ERROR);
//        boolean b = commentService.removeById(id);
//        return ResultUtils.success(b);
//    }
//
//    /**
//     * 更新（仅管理员）
//     *
//     * @param commentUpdateRequest
//     * @return
//     */
//    @PostMapping("/update")
//    public BaseResponse<Boolean> updateComment(@RequestBody CommentUpdateRequest commentUpdateRequest) {
//        if (commentUpdateRequest == null || commentUpdateRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        Comment comment = new Comment();
//        BeanUtils.copyProperties(commentUpdateRequest, comment);
//        // 参数校验
//        commentService.validComment(comment, false);
//        long id = commentUpdateRequest.getId();
//        // 判断是否存在
//        Comment oldComment = commentService.getById(id);
//        ThrowUtils.throwIf(oldComment == null, ErrorCode.NOT_FOUND_ERROR);
//        boolean result = commentService.updateById(comment);
//        return ResultUtils.success(result);
//    }
//
//    /**
//     * 根据 id 获取
//     *
//     * @param id
//     * @return
//     */
//    @GetMapping("/get/vo")
//    public BaseResponse<Comment> getCommentVOById(long id) {
//        if (id <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        Comment comment = commentService.getById(id);
//        if (comment == null) {
//            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
//        }
//        return ResultUtils.success(comment);
//    }
//
//    /**
//     * 分页获取列表（仅管理员）
//     *
//     * @param commentQueryRequest
//     * @return
//     */
//    @PostMapping("/list/page")
//    public BaseResponse<Page<Comment>> listCommentByPage(@RequestBody CommentQueryRequest commentQueryRequest) {
//        long current = commentQueryRequest.getCurrent();
//        long size = commentQueryRequest.getPageSize();
//        Page<Comment> commentPage = commentService.page(new Page<>(current, size),
//                commentService.getQueryWrapper(commentQueryRequest));
//        return ResultUtils.success(commentPage);
//    }
//
//




}
