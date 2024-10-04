package com.hspedu.chapter23.p1;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class p1 {
    // 获取类
    private Class getCls(String classFullPath) throws Exception {
        Class cls = Class.forName(classFullPath);
        return cls;
    }

    // 新建无形参实例，方法一（类直接构造）
    private Object getInstance1(Class cls) throws Exception {
        Object instance = cls.newInstance();
        return instance;
    }

    // 新建无形参实例，方法二（通过无形参构造函数）
    private Object getInstance2(Class cls) throws Exception {
        Constructor constructor = cls.getConstructor();
        return constructor.newInstance();
    }

    // 新建有形参实例，有形参的构造函数
    private Object getInstance3(Class cls, double n1, double n2) throws Exception {
        return cls.getConstructor(double.class, double.class).newInstance(n1, n2);
    }

    @Test
    public void test() throws Exception {
        // 1. 类
        Class<?> cls = getCls("com.hspedu.chapter23.p1.TestCls");
        // 2. 实例，构造函数
        Object instance1 = getInstance1(cls);
        Object instance2 = getInstance2(cls);
        Object instance3 = getInstance3(cls, 1.0, 2.0);

        // 3. 静态方法及调用，（静态方法用哪个实例都一样）
        // 有重载函数，确保在获取方法的时候传对形参的类型
        // 3.1 无形参
        Method method1 = cls.getMethod("func1");
        method1.invoke(instance1); // func1()
        // 3.2 有形参
        String[] strArr = new String[]{"张三", "李四", "王五"};
        cls.getMethod("func1", String[].class).invoke(instance2, (Object) strArr); // func1(张三李四王五)
        // 4. 实例方法，有无形参同上
        Method m = cls.getMethod("add", double.class);
        System.out.println(m.invoke(instance3, 10)); // 13.0

        // 5. 属性
        // 5.1 静态属性，和实例无关
        Field field1 = cls.getField("name");
        System.out.println(field1.get(instance1)); // xxx
        // 5.2 实例属性，和实例有关
        Field field2 = cls.getField("n1");
        System.out.println(field2.get(instance1)); // 0.0
        System.out.println(field2.get(instance3)); // 1.0

        // 获取所有方法
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName()); // add func1 func1
        }
        // 获取某一私有属性
        Field field3 = cls.getDeclaredField("privateInt");
        field3.setAccessible(true); // 取消Java语言访问检查，不然无法访问
        System.out.println(field3.get(instance3)); // 1
    }
}
