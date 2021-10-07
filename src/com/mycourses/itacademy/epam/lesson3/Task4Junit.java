package com.mycourses.itacademy.epam.lesson3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task4Junit {
    //TASK1

        public static int[] arrayCompress(int arr[]){
            for(int i=1;i<arr.length;i=i+2){
                arr[i]=0;
            }
            return arr;
        }
     //TASK2
        public static int[][] createMatrix(int arr[]) {
            int n = arr.length;
            int matrix2d[][] = new int[n][n];
            for (int i = 0; i < matrix2d.length; i++) {
                for (int j = 0; j < matrix2d[i].length; j++) {
                    matrix2d[i][j] = (int)Math.pow(arr[j], i+1);
                }
            }
            return matrix2d;
        }

        public static void main(String[] args) {
            Scanner scan=new Scanner(System.in);

            System.out.println("введите данные для первого задания (размер массива)>>");
            int k;
            while(!scan.hasNextInt()){
                scan.nextLine();
                System.out.println("введите другое значение");

            }
            k=scan.nextInt();
            int arrSrc[]=new int[k];
            //заполняем массив
            Random fill=new Random();
            for(int i=0;i<arrSrc.length;i++) {
                arrSrc[i] = fill.nextInt(10);
                if (i % 3 == 0) {
                    arrSrc[i] = -arrSrc[i];
                }
            }
            System.out.println("исходный массив"+ Arrays.toString(arrSrc));
            int [] arrDst=arrayCompress(arrSrc);
            System.out.println("изменненный массив");
            System.out.println(Arrays.toString(arrDst));
            System.out.println("\n**********************");
            System.out.println("введите данные для второго задания\nразмер одномерного массива, а следовательно и размер квадратной матрицы >>");
            while(!scan.hasNextInt()){
                scan.nextLine();
                System.out.println("введите другое значение");
            }
            k=scan.nextInt();

            int arr[]=new int  [k];
            for(int i=0;i<arr.length;i++){
                arr[i]=fill.nextInt(10);
            }
            int [][]arrayOut=createMatrix(arr);
            System.out.println("выводим матрицу");
            for(int i=0;i<arrayOut.length;i++){
                System.out.println();
                for(int j=0; j<arrayOut[i].length;j++){
                    System.out.printf("\t{  %4d }",arrayOut[i][j]);
                }
            }

        }
    }

