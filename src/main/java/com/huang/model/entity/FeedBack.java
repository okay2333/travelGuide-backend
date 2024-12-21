package com.huang.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 返回表
 * @TableName feedback
 */
@TableName(value ="feedback")
@Data
public class FeedBack implements Serializable {
    /**
     * 景点id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String status;
    private Long userId;
    private String fullName;
    private String phoneNumber;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}