package com.atguigu.schedule.test;

import com.atguigu.schedule.utils.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;

public class DBTest {
    @Test
    public void test() {
        Connection connection = JDBCUtil.getConnection();
    }
}
