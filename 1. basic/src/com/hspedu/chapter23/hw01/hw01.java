package com.hspedu.chapter23.hw01;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过反射修改私有成员变量
 */
public class hw01 {
    public static void main(String[] args) throws Exception {
        Class<Animal> cls = Animal.class;
        Animal animal = cls.newInstance();
        Field version = cls.getDeclaredField("version");
        version.setAccessible(true);
        version.set(animal, "2.0"); // 改成其他类型会报错
        // 获取修改后的属性
        Method method = cls.getMethod("getVersion");
        System.out.println(method.invoke(animal)); // 2.0

        System.out.println(animal.getClass() == cls); // true
        System.out.println(cls == Animal.class); // true
        System.out.println(animal.getClass() == Animal.class); // true
    }
}

class Animal {
    private String version = "1.0";

    public String getVersion() {
        return version;
    }
}