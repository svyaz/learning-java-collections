package com.github.svyaz;

import com.github.svyaz.javalearning.myhashtable.MyHashTable;

import java.util.Date;
import java.util.Iterator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Create myHashTable
        MyHashTable<Integer> myHashTable = new MyHashTable<>(1000);
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            myHashTable.add(Math.abs(random.nextInt() % 100));
        }

        // Use iterator
        Date start1 = new Date();
        Iterator iterator1 = myHashTable.iterator();
        int sum = 0;
        while (iterator1.hasNext()) {
            sum += (int) iterator1.next();
        }
        Date end1 = new Date();
        System.out.println("Sum:  " + sum);
        System.out.println("Time: " + (end1.getTime() - start1.getTime()) + " ms");
    }
}
