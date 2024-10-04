package com.hspedu.chapter10.finalPractise;

public class FinalPractise {
}

class Father {
    protected final String name;
    protected static final int age = 100;

    public Father(String name) {
        this.name = name;
    }

    public final String getFatherName() {
        return this.name;
    }
}

class Child extends Father {
    public Child(String name) {
        super(name);
    }

    public void changeFatherName() {
//        super.name = "1";
    }

//    @Override
//    public String getFatherName() {
//        return super.getFatherName();
//    }
}