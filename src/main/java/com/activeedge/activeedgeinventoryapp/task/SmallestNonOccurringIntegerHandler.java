package com.activeedge.activeedgeinventoryapp.task;

import java.util.Arrays;

public class SmallestNonOccurringIntegerHandler {

    public static void main(String[] args) {
        int[] array1 = {1, 3, 6, 4, 1, 2};
        int[] array2 = {5, -1, -3};
        System.out.println(getSmallestNonOccurringInteger(array1));
        System.out.println(getSmallestNonOccurringInteger(array2));
    }

    public static int getSmallestNonOccurringInteger(int[] array) {
        Arrays.sort(array);
        int minimumNumber = 1;
        for (int j : array) {
            if (j == minimumNumber) {
                minimumNumber++;
            }
        }
        return minimumNumber;
    }

}
