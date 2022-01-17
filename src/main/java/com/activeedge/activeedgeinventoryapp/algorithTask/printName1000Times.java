package com.activeedge.activeedgeinventoryapp.algorithTask;

public class printName1000Times {

    public static void main(String[] args) {
        printName();
    }

    public static void printName() {
        String name = "Adedotun";
        String str = "x";
        str = str.replaceAll("x", "xxxxxxxxxx");
        str = str.replaceAll("x", "xxxxxxxxxx");
        str = str.replaceAll("x", "xxxxxxxxxx");
        str = str.replaceAll("x", name + "\n");
        System.out.println(str.split("\n").length);
    }

}
