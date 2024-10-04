package com.hspedu.chapter10.innerClass;

public class MemberInnerClass {
    public static void main(String[] args) {
        Outer2 outer2 = new Outer2();
        Outer2.Inner2 inner2 = outer2.new Inner2(); // 具名类
        inner2.hi();
        // 或者也可以写个方法返回创建好的内部类的实例
        Outer2.Inner2 inner2_1 = outer2.getInnerInstance();
        inner2_1.hi(); // 效果同上
        outer2.person.hi(); // 匿名类，在属性阶段就实例化了，这里直接调用
    }
}

class Outer2 {
    private int n1 = 10;

    public final class Inner2 extends Person {
        private int n1 = 20;

        @Override
        public void hi() {
            System.out.println("成员内部类的hi()，非匿名");
            System.out.println(Outer2.this.n1);
            System.out.println(n1);
        }
    }

    public Inner2 getInnerInstance() {
        return new Inner2();
    }

    public final Person person = new Person() {
        private int n1 = 30;

        @Override
        public void hi() {
            System.out.println("成员内部类的hi()，匿名");
            System.out.println(Outer2.this.n1);
            System.out.println(n1);
        }
    };

}

