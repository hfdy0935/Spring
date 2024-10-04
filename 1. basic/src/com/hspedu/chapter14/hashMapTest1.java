package com.hspedu.chapter14;

import java.util.*;

public class hashMapTest1 {
    public static void main(String[] args) {
        Map map = new HashMap();
        HashMap hashMap = new HashMap();

        map.put("no1", "1");
        map.put("邓超", "孙俪");
        map.put("王宝强", "马蓉");


        // 1. 获取key集合
        Set<String> keyset = map.keySet();
        // 增强for循环遍历keyset
        for (String key : keyset) {
            System.out.println(map.get(key));
        }
        // 迭代器遍历keyset
        Iterator iterator = keyset.iterator();
        while (iterator.hasNext()) {
            System.out.println(map.get(iterator.next()));
        }
        System.out.println("-----------------------");

        // 2. 获取values
        Collection<String> values = map.values();
        for (String value : values) {
            System.out.println(value);
        }
        Iterator iterator1 = values.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
        System.out.println("-----------------------");

        // 3. 获取entrySet
        // 不用泛型就需要先从Object向下转型
        Set<Map.Entry> entrySet = map.entrySet();
        for (Map.Entry m : entrySet) {
            System.out.println(m.getKey() + " " + m.getValue());
        }
        Iterator<Map.Entry> iterator2 = entrySet.iterator();
        while (iterator2.hasNext()) {
            Map.Entry m = iterator2.next();
            System.out.println(m.getKey() + " " + m.getValue());
        }
    }
}
