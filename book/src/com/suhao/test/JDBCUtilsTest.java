package com.suhao.test;

import com.suhao.utils.JDBCUtils;
import org.junit.Test;

public class JDBCUtilsTest {

    @Test
    public void testJDBCUtils(){
        for(int i = 0; i < 100; i++){
            System.out.println(JDBCUtils.getConnection());
        }
    }
}
