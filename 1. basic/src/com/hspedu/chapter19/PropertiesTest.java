package com.hspedu.chapter19;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        String filePath = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\mysql.properties";
        prop.load(new FileReader(filePath));
        // 获取
        prop.list(System.out);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        System.out.println("用户名：" + username + " 密码：" + password);

        // 修改
        prop.setProperty("username", "张三"); // 保存的是中文的unicode码值，读取的时候正常显示
        prop.setProperty("password", "098765");
        prop.setProperty("lastModified", LocalDateTime.now().toString());
        prop.store(new FileOutputStream(filePath), null);
        System.out.println("保存成功");
    }
}
