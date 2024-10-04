package com.hspedu.chapter25.p3;

import org.junit.Test;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Statement练习
 */
public class p3 {
    private String url = "jdbc:mysql://127.0.0.1:3306/test";

    @Test
    public void test1() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src\\com\\hspedu\\chapter25\\p2\\mysql.properties"));
        String url = prop.getProperty("baseUrl") + "/" + prop.getProperty("database");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stat = conn.createStatement();
        // 1. 创建news表
        String sql1 = "create table if not exists news(" +
                "`id` int(11) primary key AUTO_INCREMENT," +
                "content longtext not null)";
        stat.execute(sql1);
        // 2. 添加数据
        String sql2 = "insert into news(content) values('震惊!'),('新闻'),('头条'),('快讯'),('轶事')";
        int res2 = stat.executeUpdate(sql2);
        if (res2 <= 0) {
            System.out.println("插入失败");
            return;
        }
        System.out.println("插入成功，共插入" + res2 + "行");
        // 3. 修改id=1的content
        String sql3 = "update news set content=concat(content,'改了') where id=1";
        int res3 = stat.executeUpdate(sql3);
        if (res3 != 1) {
            System.out.println("修改失败");
            return;
        }
        System.out.println("修改成功，共修改" + res3 + "行");
        // 4. 删除id=3的行
        String sql4 = "delete from news where id=3";
        int res4 = stat.executeUpdate(sql4);
        if (res4 != 1) {
            System.out.println("删除失败");
            return;
        }
        // 5. 选取所有id>3的行
        // Statement
        String sql5 = "select * from news where id>3";
        ResultSet resultSet1 = stat.executeQuery(sql5);
        while (resultSet1.next()) {
//            int id= resultSet1.getInt(1); // 第一列
            int id1 = resultSet1.getInt("id"); // 根据列名获取，推荐
            String content = resultSet1.getString("content");
            System.out.println("id = " + id1 + ", content = " + content);
            // id = 4, content = 快讯
            // id = 5, content = 轶事
        }

        stat.close();
        conn.close();
        resultSet1.close();
    }


    @Test
    public void test2() throws Exception {
        Connection conn = DriverManager.getConnection(url, "root", "1952620883Zzw#");
        // 1. 创建admin表，这里可用可不用PrepareStatement
        String sql1 = "create table if not exists admin(" +
                "id int(10) primary key auto_increment," +
                "`name` varchar(10) not null)";
        PreparedStatement ps1 = conn.prepareStatement(sql1);
        ps1.execute();

        // 2. 添加数据
        ArrayList<Person> personArr = getPersonArr();
        String sql2 = "insert into admin values(?,?)";
        for (Person person : personArr) {
            PreparedStatement ps = conn.prepareStatement(sql2);
            ps.setInt(1, person.id);
            ps.setString(2, person.name);
            ps.executeUpdate();
        }

        // 3. 修改
        String sql3 = "update admin set name=? where name=?";
        PreparedStatement ps3 = conn.prepareStatement(sql3);
        ps3.setString(1, "king");
        ps3.setString(2, "tom");
        ps3.executeUpdate();

        // 4. 删除
        String sql4 = "delete from admin where id=?";
        PreparedStatement ps4 = conn.prepareStatement(sql4);
        ps4.setInt(1, 2);
        ps4.executeUpdate();

        // 5. 查询
        String sql5 = "select * from admin";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql5);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println("id = " + id + ", name = " + name);
            // id = 1, name = 张三
            // id = 3, name = 王五
            // id = 4, name = 赵六
            // id = 5, name = 田七
        }
    }


    private ArrayList<Person> getPersonArr() {
        ArrayList<Person> personArr = new ArrayList<>();
        personArr.add(new Person(1, "张三"));
        personArr.add(new Person(2, "李四"));
        personArr.add(new Person(3, "王五"));
        personArr.add(new Person(4, "赵六"));
        personArr.add(new Person(5, "田七"));
        return personArr;
    }
}

class Person {
    public int id;
    public String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}