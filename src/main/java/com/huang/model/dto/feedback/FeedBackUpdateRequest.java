package com.huang.model.dto.feedback;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class FeedBackUpdateRequest implements Serializable {

    private Long id;
    private String title;
    private String content;
    private String status;
    private String fullName;
    private String phoneNumber;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
