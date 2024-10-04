package com.hspedu.chapter15.p2;


import java.util.*;

public class p2 {
    public static void main(String[] args) {
        HashSet<Student> studentsSet = new HashSet<>();
        studentsSet.add(new Student("张三", 20));
        studentsSet.add(new Student("李四", 21));
        studentsSet.add(new Student("王五", 22));

        System.out.println("--------------------------HashSet--------------------------");
        // 遍历HashSet
        // 1 增强for循环
        for (Student stu : studentsSet) {
            System.out.println(stu);
        }
        // 2 迭代器
        Iterator<Student> iterator0 = studentsSet.iterator();
        while (iterator0.hasNext()) {
            Student s = iterator0.next();
            System.out.println((s));
        }
        // 如果是ArrayList，还可以用下标遍历

        System.out.println("--------------------------HashMap--------------------------");
        HashMap<String, Student> studentsMap = new HashMap<>();
        studentsMap.put("张三", new Student("张三", 20));
        studentsMap.put("李四", new Student("李四", 21));
        studentsMap.put("王五", new Student("王五", 22));
        // 遍历hashMap
        // 1 keys
        Set<String> keys = studentsMap.keySet();
        // 1.1
        for (String key : keys) {
            Student student = studentsMap.get(key);
            System.out.println(student);
        }
        // 1.2
        Iterator<String> iterator1 = keys.iterator();
        while (iterator1.hasNext()) {
            Student student = studentsMap.get(iterator1.next());
            System.out.println(student);
        }

        // 2 values
        Collection<Student> values = studentsMap.values();
        // 2.1
        for (Student s : values) {
            System.out.println(s);
        }
        // 2.2
        Iterator<Student> iterator2 = values.iterator();
        while (iterator2.hasNext()) {
            Student s = iterator2.next();
            System.out.println(s);
        }
        // 3 entry
        Set<Map.Entry<String, Student>> entries = studentsMap.entrySet();
        // 3.1
        for (Map.Entry<String, Student> entry : entries) {
            System.out.println(entry.getValue());
        }
        // 3.2
        Iterator<Map.Entry<String, Student>> iterator3 = entries.iterator();
        while (iterator3.hasNext()) {
            Student s = iterator3.next().getValue();
            System.out.println(s);
        }
    }
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }
}