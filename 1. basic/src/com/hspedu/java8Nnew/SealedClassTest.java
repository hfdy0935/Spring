package com.hspedu.java8Nnew;

import org.junit.Test;

public class SealedClassTest {
    @Test
    public void test1() {

    }
}

sealed class Person permits Student, Teacher, Worker {

}

final class Student extends Person {
}


sealed class Teacher extends Person permits ChineseTeacher {
}

final class ChineseTeacher extends Teacher {
}

non-sealed class Worker extends Person {
}
//class Football extends Person{}