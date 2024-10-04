package com.hspedu.chapter15.hw01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class hw01 {
    public static void main(String[] args) {
        Dao<User> userDao = new Dao<>();
        userDao.save("张三", new User(0, 0, "张三"));
        userDao.save("李四", new User(1, 1, "李四"));
        System.out.println(userDao.get("张三"));
        userDao.update("张三", new User(2, 2, "张三1"));
        System.out.println(userDao.get("张三"));
        System.out.println(userDao.list());
        userDao.delete("张三");
        System.out.println(userDao);
    }
}


class Dao<T> {
    private Map<String, T> map = new HashMap<>();

    public void save(String id, T entity) {
        map.put(id, entity);
    }

    public T get(String id) {
        return map.get(id);
    }

    public void update(String id, T entity) {
        map.put(id, entity);
    }

    public List<T> list() {
        return new ArrayList<>(map.values());
    }

    public void delete(String id) {
        map.remove(id);
    }

    @Override
    public String toString() {
        return map.toString();
    }
}

class User {
    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", age=" + age + ", name=" + name + "]";
    }
}