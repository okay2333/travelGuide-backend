// CommentService.java
package com.huang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.entity.Comment;
import com.huang.model.vo.CommentVO;

import java.util.List;

public interface CommentService extends IService<Comment> {
    void validComment(Comment comment, boolean isNew);
    Page<Comment> listCommentsByPostId(long postId, long current, long size);
    List<CommentVO> getCommentsWithReplies(long postId);
}