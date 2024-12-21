package com.huang.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName(value ="vrtour")
@Data
public class VRTour  implements Serializable {
    private Long id;
    private String cover;
    private String picture;
    private String name;
    private String location;
    private static final long serialVersionUID = 1L;
}
