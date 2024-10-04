package com.hspedu.chapter08.hw13;

public class Teacher extends Person {
    private final String work_id;

    public Teacher(String name, int age, char gender, String work_id) {
        super(name, age, gender);
        this.work_id = work_id;
    }

    public void teach() {
        System.out.println("我承诺，我会好好教学。");
    }

    @Override
    public String play() {
        return super.play() + "象棋";
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("stu_id：" + this.work_id);
    }

}
