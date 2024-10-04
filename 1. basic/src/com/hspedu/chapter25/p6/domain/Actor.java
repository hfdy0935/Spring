package com.hspedu.chapter25.p6.domain;

import java.util.Date;


public class Actor {
    private Integer id;
    private String name;
    private String gender;
    private String birthday;

    public Actor() {
        // 一定要给一个无参构造器，反射需要
    }

    public Actor(Integer id, String name, String gender, String birthday) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        return "\nActor{" +
                "id = " + id +
                ", name = " + name +
                ", gender = " + gender +
                ",birthday = " + birthday +
                "}";
    }
}
