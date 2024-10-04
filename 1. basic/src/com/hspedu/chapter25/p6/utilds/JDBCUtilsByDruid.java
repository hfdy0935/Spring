package com.hspedu.chapter25.p6.utilds;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtilsByDruid {
    private static DataSource ds;

    static {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src\\com\\hspedu\\chapter25\\p6\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}


