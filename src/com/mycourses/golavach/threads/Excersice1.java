package com.mycourses.golavach.threads;

import java.util.Random;

public class Excersice1 {
    public static void main(String[] args) {


        new Thread(new Runnable(){
            public void run(){
                Random rd =new Random();
                int[] arr=new int[10];
                //заполняем массив
                for(int k=0;k<arr.length;k++){
                    arr[k]=rd.nextInt(10);
                }
                System.out.println("выводим несортированый массив");
                for(int iter:arr){
                    System.out.print(iter+" ");
                }
//                try {
//                    Thread.sleep(1);
//                }catch (InterruptedException exc){
//                    /*NOP*/
//                }


            };
        }).start();
        System.out.println("выполняем основной поток");

    }
}
