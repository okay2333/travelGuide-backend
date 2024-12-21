package com.huang.model.dto.feedback;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class FeedBackAddRequest implements Serializable {
        private String title;
        private String content;
        private String fullName;
        private String phoneNumber;
        private static final long serialVersionUID = 1L;
}
