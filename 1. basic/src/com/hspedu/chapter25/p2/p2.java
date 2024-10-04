package com.hspedu.chapter25.p2;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * 连接方式
 */
public class p2 {
    private String url = "jdbc:mysql://127.0.0.1:3306/test";

    private Properties getProp() {
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "1952620883Zzw#");
        return info;
    }

    @Test
    public void connect1() throws SQLException {
        // 既然已经引入Driver了，就没必要com. ...了
        Driver driver = new com.mysql.jdbc.Driver();
        Properties info = getProp();
        Connection conn = driver.connect(url, info);
        printConnectionInfoAndClose(conn);
    }

    @Test
    public void connect2() throws Exception {
        Class cls = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) cls.newInstance();
        Properties info = getProp();
        Connection conn = driver.connect(url, info);
        printConnectionInfoAndClose(conn);
    }

    @Test
    public void connect3() throws Exception {
        Class cls = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) cls.newInstance();
        DriverManager.registerDriver(driver); //
        Properties info = getProp();
        // 两种方式
        Connection connection1 = DriverManager.getConnection(url, info);
        Connection connection2 = DriverManager.getConnection(url, info.getProperty("user"), info.getProperty("password"));
        printConnectionInfoAndClose(connection1, connection2);
    }

    @Test
    public void connect4() throws Exception {
        // mysql驱动5.1.6之后可以无需下面这句
        // Class.forName("com.mysql.jdbc.Driver");
        Properties info = getProp();
        Connection connection1 = DriverManager.getConnection(url, info);
        Connection connection2 = DriverManager.getConnection(url, info.getProperty("user"), info.getProperty("password"));
        printConnectionInfoAndClose(connection1, connection2);
    }


    private void printConnectionInfoAndClose(Connection... connections) {
        for (Connection connection : connections) {
            System.out.println(connection);
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("未关闭");
            }
        }
    }
}
