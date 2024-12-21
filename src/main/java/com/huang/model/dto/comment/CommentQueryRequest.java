package com.huang.model.dto.comment;

// CommentQueryRequest.java
import lombok.Data;

@Data
public class CommentQueryRequest {
    private Long postId;
    private Long current;
    private Long pageSize;
}