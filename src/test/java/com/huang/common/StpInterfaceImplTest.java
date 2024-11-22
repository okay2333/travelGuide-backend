package com.huang.common;


import org.junit.jupiter.api.Test;

import java.util.List;

public class StpInterfaceImplTest {
    @Test
    void listPostWithDelete() {
        StpInterfaceImpl stpInterfaceImpl = new StpInterfaceImpl();
        List<String> admin = stpInterfaceImpl.getRoleList(1828339688465825793L, "null");
        System.out.println(admin);
    }
}
