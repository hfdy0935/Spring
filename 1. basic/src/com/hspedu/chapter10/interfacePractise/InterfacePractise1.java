package com.hspedu.chapter10.interfacePractise;

public class InterfacePractise1 {
    public static void main(String[] args) {
        Camera camera = new Camera();
        camera.start();
        Phone phone = new Phone();
        phone.start();
    }
}

class Camera implements UseInterface {
    @Override
    public void start() {
        System.out.println("相机启动");
    }

    @Override
    public void stop() {
        System.out.println("相机停止");
    }
}

class Phone implements UseInterface {
    @Override
    public void start() {
        System.out.println("手机启动");
    }

    @Override
    public void stop() {
        System.out.println("手机停止");
    }
}