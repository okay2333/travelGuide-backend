package com.huang.model.entity;

import lombok.Data;

@Data
public class CountTicket {
    private String name;   // 景点名称
    private int value;     // 剩余门票数量
}
