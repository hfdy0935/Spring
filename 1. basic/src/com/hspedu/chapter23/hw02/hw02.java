package com.hspedu.chapter23.hw02;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;

public class hw02 {

    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("java.io.File");
        Constructor<?>[] constructors = cls.getConstructors();
        for (Constructor<?> c : constructors) {
            System.out.println(c.getName());
        }
        // 获取需要path的构造器
        Constructor<?> constructor = cls.getConstructor(String.class);
        String path = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\chapter23\\hw02\\test.txt";
        // Object向下转型
        File file = (File) constructor.newInstance(path);
        file.createNewFile();
        FileWriter fw = new FileWriter(path);
        fw.write("写入的内容");
        fw.close();
    }
}
