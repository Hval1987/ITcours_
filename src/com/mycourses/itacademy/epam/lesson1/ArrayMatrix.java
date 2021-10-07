package com.mycourses.itacademy.epam.lesson1;

public class ArrayMatrix {
    public static void main(String[] args) {
        int n=5;
        int arr[][]=new int[n][n];
        for(int i=0;i<arr.length;i++) {
            System.out.println();
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][i] =i;
            }
        }
        for(int i=0;i<arr.length;i++) {
            System.out.println();
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print("[  " + arr[i][j] + "]");
            }
        }

    }
}
