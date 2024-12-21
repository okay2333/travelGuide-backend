package com.huang.model.dto.vrtour;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class VRTourUpdateRequest implements Serializable {

    private Long id;
    private String cover;
    private String picture;
    private String name;
    private String location;
    private static final long serialVersionUID = 1L;
}
