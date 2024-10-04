package com.hspedu.chapter25.p5;

import com.hspedu.chapter25.JDBCUtils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 批处理
 */
public class p5 {
    /**
     * 测试不批处理，添加5000条信息
     */
    @Test
    public void withoutBatch() throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        long start = System.currentTimeMillis();
        String sql = "insert into admin values(null,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < 5000; i++) {
            ps.setString(1, "withoutBatch" + i);
            ps.executeUpdate();
        }
        System.out.println("无批量耗时" + (System.currentTimeMillis() - start)); // 5731
        JDBCUtils.close(null, ps, conn);
    }

    @Test
    public void withBatch() throws SQLException {
        Connection conn = JDBCUtils.getConnection(true);
        long start = System.currentTimeMillis();
        String sql = "insert into admin values(null,?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (int i = 0; i < 5000; i++) {
            ps.setString(1, "withBatch" + i);
            ps.addBatch();
            // 有1000条记录时，再批量执行
            if ((i + 1) % 1000 == 0) {
                ps.executeBatch();
                // 清空批
                ps.clearBatch();
            }
        }
        System.out.println("批量耗时" + (System.currentTimeMillis() - start)); // 5584
        JDBCUtils.close(null, ps, conn);
    }
}
