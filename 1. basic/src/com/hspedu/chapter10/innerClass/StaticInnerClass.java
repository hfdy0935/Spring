package com.hspedu.chapter10.innerClass;


public class StaticInnerClass {
    public static void main(String[] args) {
        Outer3.Inner3 inner3 = new Outer3.Inner3();
        inner3.hi();
        // 或者也可以写个方法返回创建好的内部类的实例
        Outer3.Inner3 inner3_1 = Outer3.getInnerInstance();
        inner3_1.hi(); // 效果同上
        Outer3.person.hi(); // 匿名类，在属性阶段就实例化了，这里直接调用
    }
}

class Outer3 {
    static int n1 = 10;

    public static final class Inner3 extends Person {
        static int n1 = 20;

        @Override
        public void hi() {
            System.out.println("成员内部类的hi()，静态非匿名");
            System.out.println("Outer3 的 n1 = " + Outer3.n1);
            System.out.println(Inner3.n1);
        }
    }

    public static Inner3 getInnerInstance() {
        return new Inner3();
    }

    public static final Person person = new Person() {
//        static int n1 = 30;

        @Override
        public void hi() {
            System.out.println("成员内部类的hi()，静态匿名");
            System.out.println("Outer3 的 n1 = " + Outer3.n1);
        }
    };

}

