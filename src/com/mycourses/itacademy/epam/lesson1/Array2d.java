package com.mycourses.itacademy.epam.lesson1;

import java.util.Random;

public class Array2d {
    public static void main(String[] args) {
        int arr[][] = new int[5][6];
        Random fill = new Random();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = fill.nextInt(100);
                if (arr[i][j] % 3 == 0) {
                    arr[i][j] = -arr[i][j];
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println();
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("\t[%4d]", arr[i][j]);

            }
        }
        System.out.println("\n**********************");
        System.out.println("вывводим первую и последнюю строку");
        for (int j = 0; j < arr[0].length; j++) {
            System.out.printf("\t{%5d}", arr[0][j]);
        }
        System.out.println();
        for (int j = 0; j < arr[arr.length - 1].length; j++) {
            System.out.printf("\t{%5d}", arr[arr.length - 1][j]);
        }
        System.out.println("\n**********************");
        System.out.println("выводим все строки с четными индексами");
        for (int i = 0; i < arr.length; i = i + 2) {
            System.out.println();
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("\t{%5d}", arr[i][j]);
            }

        }
        System.out.println("\n**********************");
//        System.out.println("вывести первый и последний  столбцы");
//        System.out.println("первый");
//        for(int i=0;i<arr.length;i++){
//            System.out.println("{"+arr[i][0]+"}");
//            }
//        System.out.println("последний");
//        for(int i=0;i<arr.length;i++) {
//            System.out.println("{" + arr[i][arr[i].length - 1] + "}");
//        }
        System.out.println("\n**********************");
        System.out.println("найти сумму отрицательных нечетных элементов");
        int summ = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (((Math.abs(arr[i][j])) % 2 == 1) && arr[i][j] < 0) ;
                summ += arr[i][j];

            }
        }
        System.out.println("сумма равна -" + summ);
        System.out.println("\n**********************");
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length - 1; j++) {
                System.out.printf("\t{%5d}", arr[i][j]);
            }
            System.out.println();
            i++;
            if (i >= arr.length) {
                break;
            }
            for (int j = arr[i].length - 1; j > 0; j--) {
                System.out.printf("\t{%5d}", arr[i][j]);


            }
            System.out.println();
        }
    }
}

