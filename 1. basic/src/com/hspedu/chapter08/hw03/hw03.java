package com.hspedu.chapter08.hw03;

/**
 * 教师类，传姓名、年龄、职称、薪资，introduce方法
 * 教授、副教授、讲师三个类继承教师类，并重写introduce方法
 */
public class hw03 {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("张三", 20, "不知道", 100);
        teacher.introduce();
        AssociateProfessor associateProfessor = new AssociateProfessor("李四", 22, 20);
        associateProfessor.introduce();
    }
}