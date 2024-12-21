package com.huang.model.dto.feedback;

import com.huang.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
public class FeedBackQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;
    private String title;
    private String content;
    private String status;
    private String fullName;
    private String phoneNumber;



    private static final long serialVersionUID = 1L;
}
