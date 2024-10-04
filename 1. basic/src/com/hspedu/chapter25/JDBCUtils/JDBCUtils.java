package com.hspedu.chapter25.JDBCUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;

/**
 * 封装工具
 */
public class JDBCUtils {
    private static String user;
    private static String password;
    private static String url;
    private static String driver;

    static {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src\\com\\hspedu\\chapter25\\JDBCUtils\\mysql.properties"));
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            url = prop.getProperty("url");
            driver = prop.getProperty("driver");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 连接数据库，返回Connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 需要批量处理的清空
    public static Connection getConnection(boolean needBatch) {
        String url1 = needBatch ? "?rewriteBatchedStatements=true" : "";
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 关闭
    public static void close(ResultSet set, Statement statement, Connection connection) {
        try {
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}









