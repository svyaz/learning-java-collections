package com.github.svyaz;

import com.github.svyaz.javalearning.singlylinkedlist.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToHead("Hi there!");
        list.addToTail("I");
        list.addToTail("like");
        list.addToTail("Java");
        System.out.println("list size: " + list.size());
        System.out.println("list: " + list.toString());
        System.out.println();

        System.out.println("Remove element: " + list.remove("Hi there!"));
        System.out.println("list size: " + list.size());
        System.out.println("list: " + list.toString());
        System.out.println();

        System.out.println("Now turn the list...");
        list.turn();
        System.out.println("list size: " + list.size());
        System.out.println("list: " + list.toString());
        System.out.println();

        System.out.println("Add by index...");
        list.add(3, "?");
        System.out.println("list size: " + list.size());
        System.out.println("list: " + list.toString());
        System.out.println();
    }
}
