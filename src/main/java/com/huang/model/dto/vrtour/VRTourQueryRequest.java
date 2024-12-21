package com.huang.model.dto.vrtour;

import com.huang.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class VRTourQueryRequest extends PageRequest implements Serializable {

    private Long id;
    private String cover;
    private String name;
    private String location;

    private static final long serialVersionUID = 1L;
}
