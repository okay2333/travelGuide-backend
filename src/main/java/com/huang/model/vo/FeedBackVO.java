package com.huang.model.vo;

import cn.hutool.json.JSONUtil;
import com.huang.model.entity.Scenic;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 景区视图
 *

 */
@Data
public class FeedBackVO implements Serializable {

    private Long id;
    private String title;
    private String content;
    private String status;
    private Long userId;
    private String fullName;
    private String phoneNumber;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private UserVO user;




}
