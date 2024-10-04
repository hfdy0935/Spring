package com.hspedu.chapter08.hw13;

public class hw13 {
    public static void main(String[] args) {
        Student s1 = new Student("张三1", 20, '男', "1");
        Student s2 = new Student("张三2", 21, '男', "2");
        Teacher t1 = new Teacher("李四3", 30, '女', "3");
        Teacher t2 = new Teacher("李四4", 17, '女', "4");
        s1.printInfo();
        t1.printInfo();
        System.out.println(s1.play());
        System.out.println(t1.play());

        // 多态数组
        Person[] personArr = {s1, s2, t1, t2};
        for (int i = 0; i < personArr.length; i++) {
            for (int j = 0; j < personArr.length - 1 - i; j++) {
                if (personArr[j].age > personArr[j + 1].age) {
                    Person person = personArr[j];
                    personArr[j] = personArr[j + 1];
                    personArr[j + 1] = person;
                }
            }
        }
        System.out.println("排序完毕，结果如下：");
        for (Person p : personArr) {
            p.printInfo();
        }

        // 调用method
        hw13 hw13_ = new hw13();
        hw13_.method(s1);
        hw13_.method(t1);
    }

    public void method(Person p) {
        if (p instanceof Student) {
            ((Student) p).study();
        } else if (p instanceof Teacher) {
            ((Teacher) p).teach();
        }
    }
}
