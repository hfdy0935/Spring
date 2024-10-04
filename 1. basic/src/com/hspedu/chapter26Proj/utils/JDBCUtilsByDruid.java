package com.hspedu.chapter26Proj.utils;

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
    private static Properties prop = new Properties();

    static {
        try {
            prop.load(new FileInputStream("D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\manHanLou\\mysql.properties"));
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


