package com.atguigu.schedule.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.Getter;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


@SuppressWarnings({"all"})
public class JDBCUtil {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        String propPath = "D:\\桌面\\t\\Java\\tomcat_test\\web_app\\schedule-system\\resources\\jdbc.properties";
//        String propPath = "resources/jdbc.properties";
        try {
            properties.load(new FileInputStream(propPath));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取连接池
     *
     * @return DataSource
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            threadLocal.set(conn);
        }
        return conn;
    }

    /**
     * 归还连接
     */
    public static void releaseConnection() {
        Connection conn = threadLocal.get();
        if (conn != null) {
            threadLocal.remove();
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
