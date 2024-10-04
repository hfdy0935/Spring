package com.hspedu.chapter13.ArraysPractise;


import java.util.Arrays;
import java.util.Comparator;

public class p1 {
    public static void main(String[] args) {
        Book[] books = new Book[4];
        books[0] = new Book("西游记", 300);
        books[1] = new Book("三国演义", 100);
        books[2] = new Book("水浒传", 200);
        books[3] = new Book("红楼梦", 150);
        // 降序
        Arrays.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.price - o1.price > 0 ? 1 : o2.price == o1.price ? 0 : -1;
            }
        });
        for (Book book : books) {
            System.out.println(book.price);
        }

        // 升序
        Arrays.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.price - o2.price > 0 ? 1 : o1.price == o2.price ? 0 : -1;
            }
        });
        for (Book book : books) {
            System.out.println(book.price);
        }
    }
}

class Book {
    public String name;
    public double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }
}