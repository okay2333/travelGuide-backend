package com.huang.model.dto.vrtour;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class VRTourAddRequest implements Serializable {

        private String picture;
        private String cover;
        private String name;
        private String location;
        private static final long serialVersionUID = 1L;
}
