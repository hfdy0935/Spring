package com.hspedu.chapter25.JDBCUtils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUtil {
    @Test
    public void test() {
        Connection conn = null;
        String sql = "select * from admin where id=?";
        PreparedStatement ps = null;
        ResultSet set = null;

        try {
            conn = JDBCUtils.getConnection();
            System.out.println(conn.getClass()); // class com.mysql.jdbc.JDBC4Connection
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            // 执行
            set = ps.executeQuery();
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                System.out.println("id = " + id + ", name = " + name); // id = 1, name = 张三
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtils.close(set, ps, conn);
        }
    }
}
