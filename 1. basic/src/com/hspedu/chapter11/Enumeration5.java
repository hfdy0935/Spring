package com.hspedu.chapter11;

public class Enumeration5 {
    public static void main(String[] args) {
        Music.CLASSICMUSIC.playing();
        IPlaying iPlaying = new IPlaying() {
            @Override
            public void playing() {
                System.out.println("暴风影音");
            }
        };
        iPlaying.playing();
    }
}

interface IPlaying {
    void playing();
}

enum Music implements IPlaying {
    CLASSICMUSIC;
    
    @Override
    public void playing() {
        System.out.println("播放音乐");
    }
}