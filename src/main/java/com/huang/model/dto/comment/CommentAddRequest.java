package com.huang.model.dto.comment;

// CommentAddRequest.java

import lombok.Data;

@Data
public class CommentAddRequest {
    private Long postId;
    private Long parentId;
    private String content;
}