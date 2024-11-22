package com.huang.model.dto.reservationsTime;

import lombok.Data;

@Data
public class Traveler {
     /**
     * 姓名
     */
    private String fullName;

    /**
     * 身份证
     */
    private String idNumber;

    /**
     * 手机号
     */
    private String phoneNumber;
}
