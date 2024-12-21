package com.huang.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName(value ="article")
@Data
public class Article implements Serializable {
    private Long id;
    private String title;
    private String cover;
    private String content;
    private Date createTime;
    private Date updateTime;
    private static final long serialVersionUID = 1L;
}
