package com.mycourses.itacademy.epam.lesson2;

import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.util.Scanner;

public class Arrays1 {
    public static void main(String[] args) {
        int [] arr=new int[10];
        Random rnd=new Random();
        System.out.println("заполняем массив..");
        for(int i=0; i<arr.length;i++){
            arr[i]=rnd.nextInt(100);
        }
        System.out.println("\n______________________");
        for(int iter:arr) {
            System.out.print("["+iter+"] ");
        }
        System.out.println();
        System.out.println("сортирум массив...");
        for(int bound=0;bound<arr.length-1;bound++){
            for(int index=arr.length-1;index>bound;index--){
                if(arr[bound]<arr[index]){
                    int tmp=arr[index];
                    arr[index]=arr[bound];
                    arr[bound]=tmp;
                }

            }
        }
        for(int iter:arr) {
            System.out.print("["+iter+"] ");
        }
       // System.out.println("минимальное значение элемента массива -"+ min);
        //
    }

}
