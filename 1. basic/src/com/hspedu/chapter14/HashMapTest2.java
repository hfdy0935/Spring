package com.hspedu.chapter14;

import java.util.*;

public class HashMapTest2 {
    public static void main(String[] args) {
        HashMap employeeHashMap = new HashMap();
        employeeHashMap.put("001", new Employee1("张三", 20000, new RecordDate(2000, 1, 1)));
        employeeHashMap.put("002", new Employee1("李四", 15000, new RecordDate(2000, 1, 2)));
        employeeHashMap.put("003", new Employee1("王五", 30000, new RecordDate(2005, 1, 1)));
        // 遍历，找到工资>18000的
        // 1.
        Set<String> keySet = employeeHashMap.keySet();
        for (String key : keySet) {
            Employee1 employee1 = (Employee1) employeeHashMap.get(key);
            if (employee1.getSal() > 18000) {
                System.out.println(employee1.getName() + "\t" + employee1.getSal() + "\t\t" + key);
            }
        }
        // 2.
        Collection<Employee1> values = employeeHashMap.values();
        for (Employee1 employee1 : values) {
            if (employee1.getSal() > 18000) {
                // 少了key
                System.out.println(employee1.getName() + "\t" + employee1.getSal());
            }
        }

        // 3.
        Set<Map.Entry> entrySet = employeeHashMap.entrySet();
        for (Map.Entry entry : entrySet) {
            Employee1 employee1 = (Employee1) entry.getValue();
            if (employee1.getSal() > 18000) {
                System.out.println(employee1.getName() + "\t" + employee1.getSal() + "\t\t" + entry.getKey());
            }
        }
    }
}

class Employee1 {
    private String name;
    private double sal;
    private RecordDate recordDate;

    public Employee1(String name, double sal, RecordDate recordDate) {
        this.name = name;
        this.sal = sal;
        this.recordDate = recordDate;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public RecordDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(RecordDate recordDate) {
        this.recordDate = recordDate;
    }
}

class RecordDate {
    public int year;
    public int month;
    public int day;

    public RecordDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}