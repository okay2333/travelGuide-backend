package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.model.entity.Post;
import com.huang.model.entity.Scenic;

import java.util.Date;
import java.util.List;

/**
 * 帖子数据库操作
 *
 * 
 * 
 */
public interface ScenicMapper extends BaseMapper<Scenic> {

    /**
     * 查询帖子列表（包括已被删除的数据）
     */
    List<Post> listPostWithDelete(Date minUpdateTime);

}




