package com.hspedu.chapter10.innerClass;

public class P3 {
    public static void main(String[] args) {
        Cellphone cellphone = new Cellphone();
        cellphone.alarmClock(new Bell() {
            @Override
            public void ring() {
                System.out.println("懒猪起床了");
            }
        });
        cellphone.alarmClock(new Bell() {
            @Override
            public void ring() {
                System.out.println("小伙伴上课了");
            }
        });
    }

}

interface Bell {
    void ring();
}

class Cellphone {
    public void alarmClock(Bell bell) {
        System.out.println((bell.getClass()));
        bell.ring();
    }
}