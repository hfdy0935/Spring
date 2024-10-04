package com.hspedu.chapter25.p1;


import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class p1 {
    public static void main(String[] args) throws SQLException {
        // 1. 注册驱动
        Driver driver = new Driver();
        // 2. 建立连接
        // url，使用test数据库
        String url = "jdbc:mysql://localhost:3306/test";
        // 配置，也可以从.properties文件读取
        Properties prop = new Properties();
        prop.setProperty("user", "root");
        prop.setProperty("password", "1952620883Zw#");
        //
        Connection connection = driver.connect(url, prop);
        // 3. 执行sql
        // 3.1 新建表
        String sql1 = "create table if not exists actor(" +
                "id int primary key auto_increment," +
                "`name` varchar(20) not null," +
                "birthday date not null)";
        Statement stat = connection.createStatement();
        // 更普遍的执行语句，select的时候返回查的结果
        stat.execute(sql1);
        // 3.2 新增
        // 如果是dml语句，返回影响行数
        String sql2 = "insert into actor values(null,'刘德华','1970-11-11')";
        int rows = stat.executeUpdate(sql2);
        System.out.println(rows > 0 ? "插入成功" : "插入失败");
        // 3.3 查

        // 4. 关闭连接，或者使用try-with-resources不用手动关闭
        stat.close();
        connection.close();
    }
}
