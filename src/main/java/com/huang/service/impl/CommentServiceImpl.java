// CommentServiceImpl.java
package com.huang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.exception.BusinessException;
import com.huang.common.ErrorCode;
import com.huang.mapper.CommentMapper;
import com.huang.model.entity.Comment;
import com.huang.model.vo.CommentVO;
import com.huang.model.vo.UserVO;
import com.huang.service.CommentService;
import com.huang.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private UserService userService;

    @Override
    public void validComment(Comment comment, boolean isNew) {
        if (comment == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // Add more validation logic if needed
    }

    @Override
    public Page<Comment> listCommentsByPostId(long postId, long current, long size) {
        Page<Comment> page = new Page<>(current, size);
        return this.page(page, new QueryWrapper<Comment>().eq("postId", postId));
    }

    @Override
    public List<CommentVO> getCommentsWithReplies(long postId) {
        List<Comment> comments = this.list(new QueryWrapper<Comment>().eq("postId", postId));
        return comments.stream()
                .filter(comment -> comment.getParentId() == null)
                .map(comment -> convertToResponse(comment, comments))
                .collect(Collectors.toList());
    }

    private CommentVO convertToResponse(Comment comment, List<Comment> allComments) {
        CommentVO response = new CommentVO();
        response.setId(comment.getId());
        response.setPostId(comment.getPostId());
        response.setUserId(comment.getUserId());
        response.setParentId(comment.getParentId());
        response.setContent(comment.getContent());
        UserVO user = UserVO.objToVo(userService.getById(comment.getUserId()));
        response.setUser(user);
        List<CommentVO> replies = allComments.stream()
                .filter(c -> comment.getId().equals(c.getParentId()))
                .map(c -> convertToResponse(c, allComments))
                .collect(Collectors.toList());
        response.setReplies(replies);
        return response;
    }
}