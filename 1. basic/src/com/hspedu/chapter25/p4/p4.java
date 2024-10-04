package com.hspedu.chapter25.p4;

import com.hspedu.chapter25.JDBCUtils.JDBCUtils;
import org.junit.Test;

import java.sql.*;

/**
 * 事务
 */
public class p4 {
    @Test
    public void test() {
        Connection conn = null;
        String sql1 = "insert into admin values(?,?)";
        String sql2 = "select * from admin where id in (?,?,?)";
        PreparedStatement ps1 = null, ps2 = null;
        ResultSet set = null;
        Savepoint sp1 = null;

        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false); ////////////
            // 增
            ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, 6);
            ps1.setString(2, "不知道名字");
            ps1.executeUpdate();
            sp1 = conn.setSavepoint();
            // 查
            ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, 1);
            ps2.setInt(2, 4);
            ps2.setInt(3, 6);
            set = ps2.executeQuery();
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                System.out.println("id = " + id + " name=" + name);
                // id = 1 name=张三
                // id = 4 name=赵六
                // id = 6 name=不知道名字
            }

            // 提交
            conn.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                conn.rollback(sp1);
//                conn.rollback();
            } catch (SQLException e1) {
                System.out.println(e1.getMessage());
            }
        } finally {
            JDBCUtils.close(null, ps2, conn);
        }


    }
}
