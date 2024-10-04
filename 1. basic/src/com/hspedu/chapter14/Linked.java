package com.hspedu.chapter14;

public class Linked {
    public static void main(String[] args) {
        // 双向链表
        Node jack = new Node("jack");
        Node tom = new Node("tom");
        Node hsp = new Node("hsp");
        jack.next = tom;
        tom.next = hsp;
        hsp.prev = tom;
        tom.prev = jack;
        Node first = jack;
        Node last = hsp;

        // 从头到尾遍历
        while (true) {
            if (first == null) {
                break;
            }
            System.out.println(first);
            first = first.next;
        }
        System.out.println(first);
        System.out.println("---------------------------------");
        // 从尾到头遍历
        while (true) {
            if (last == null) {
                break;
            }
            System.out.println(last);
            last = last.prev;
        }
        System.out.println(last);
        System.out.println("---------------------------------");
    }
}

class Node {
    public Object item;
    public Node next;
    public Node prev;

    public Node(Object name) {
        this.item = name;
    }

    public String toString() {
        return "Node name = " + this.item;
    }
}