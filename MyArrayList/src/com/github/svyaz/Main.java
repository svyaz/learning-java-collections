package com.github.svyaz;

import com.github.svyaz.javalearning.myarraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> yearMonths = new MyArrayList<>(12);
        String unknownMonthString = "unknown month";
        yearMonths.add(unknownMonthString);

        System.out.println("=== Months of a year ===");
        System.out.println(yearMonths + ", size: " + yearMonths.size());

        yearMonths.addAll(Arrays.asList("January", "February", "March", "April", "May"));
        System.out.println(yearMonths);
        System.out.println("Removing unknown month ...");
        yearMonths.remove(unknownMonthString);
        System.out.println(yearMonths);

        yearMonths.add("June");
        yearMonths.add("July");
        yearMonths.add("August");
        yearMonths.add("September");
        yearMonths.add("October");
        yearMonths.add("November");
        yearMonths.add("December");
        System.out.println(yearMonths + ", size: " + yearMonths.size());

        System.out.println("--- print by iterator ---");

        for (String month : yearMonths) {
            System.out.println("Month: " + month);
        }

        System.out.println("Hash code: " + yearMonths.hashCode());
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(unknownMonthString);
        System.out.println("equals to arrayList: " + yearMonths.equals(arrayList));

        System.out.println("Clear list ...");
        yearMonths.clear();
        System.out.println(yearMonths + ", size: " + yearMonths.size());
    }
}
