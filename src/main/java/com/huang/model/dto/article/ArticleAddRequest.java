package com.huang.model.dto.article;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建请求
 *
 * 
 * 
 */
@Data
public class ArticleAddRequest implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片
     */
    private String cover;



    private static final long serialVersionUID = 1L;
}