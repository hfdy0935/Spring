package com.hspedu.chapter10.interfacePractise;

public class InterfacePractise2 {
    public static void main(String[] args) {
        System.out.println(IPerson.name);
    }
}


interface IFly {
    void fly();
}

interface ISwim {
    void swim();
}

/**
 * 人
 */
interface IPerson {
    String name = "张三";

    void eat();

    void sleep();
}

/**
 * 学生
 */
interface IStudent extends IPerson, IFly {
    void study();

    void handleHomework();

    void handleExam();

}

/**
 * 教师
 */
interface ITeacher extends IPerson {
    void teach();

    void organizeExam();

    void resolveProblem();
}

/**
 * 班长
 */
interface IMonitor extends IStudent {
    void watch();

    void contactTeacher();
}

class Person implements IPerson {
    @Override
    public void eat() {
        System.out.println("吃");
    }

    @Override
    public void sleep() {
        System.out.println("睡");
    }
}

class Student extends Person implements IStudent {
    @Override
    public void study() {
        System.out.println("学习");
    }

    @Override
    public void handleHomework() {
        System.out.println("写作业");
    }

    @Override
    public void handleExam() {
        System.out.println("考试");
    }

    @Override
    public void fly() {
        System.out.println("臣妾做不到啊");
    }
}

class Teacher extends Person implements ITeacher, ISwim {
    @Override
    public void teach() {
        System.out.println("教学");
    }

    @Override
    public void organizeExam() {
        System.out.println("组织考试");
    }

    @Override
    public void resolveProblem() {
        System.out.println("解决问题");
    }

    @Override
    public void swim() {
        System.out.println("正在学习游泳...");
    }
}

class Monitor extends Student implements IMonitor {
    @Override
    public void watch() {
        System.out.println("监视");
    }

    @Override
    public void contactTeacher() {
        System.out.println("联系老师");
    }
}