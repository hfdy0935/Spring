package com.hspedu.chapter14.hw03;

import java.util.HashMap;
import java.util.Map;

public class hw03 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap();
        map.put("jack", 650);
        map.put("tom", 1200);
        map.put("smith", 2900);
        System.out.println(map);

        map.put("jack", 2600);
        for (String key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
            map.put(key, map.get(key) + 100);
        }
        System.out.println(map);


    }
}
