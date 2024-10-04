package com.hspedu.chapter14;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest2 {
    public static void main(String[] args) {
        List bookList = new ArrayList();
        bookList.add(new Book("三国演义", 100, "罗贯中"));
        bookList.add(new Book("红楼梦", 80, "曹雪芹"));
        bookList.add(new Book("西游记", 300, "吴承恩"));
        for (Object b : bookList) {
            // 这里也可以向下转型再打印
            System.out.println(b);
        }
        // 冒泡排序
        for (int i = 0; i < bookList.size() - 1; i++) {
            for (int j = 0; j < bookList.size() - 1 - i; j++) {
                Book book1 = (Book) bookList.get(j);
                Book book2 = (Book) bookList.get(i + 1);
                if (book1.getPrice() > book2.getPrice()) {
                    bookList.set(j, book2);
                    bookList.set(j + 1, book1);
                }
            }
        }
        for (Object b : bookList) {
            // 这里也可以向下转型再打印
            System.out.println(b);
        }
    }
}

class Book {
    private String name;
    private double price;
    private String author;

    public Book(String name, double price, String author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "名称：" + name + "\t价格：" + price + "\t作者：" + author;
    }

}