package com.soul.test;

/**
 * @author 符浩灵
 * @date 2019/9/3 19:10
 */
public class Demo2 {

    public static void print(int [] arr){
        for(int k=0;k<arr.length;k++){
            System.out.println("值："+arr[k]);
        }
    }
    public static int[] move(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        return arr;
    }

    /**
     * 选择排序
     * @param arr
     * @return
     */
    public static int[] selectSort(int[] arr){
        for (int i=0;i<arr.length-1;i++){
            int num = arr[i]; // 记录最小值
            int index = i; // 最小值的下标
            for (int j =i+1;j<arr.length;j++){
                if(num > arr[j]){ // 前一个值比后面大
                    num = arr[j];
                    index = j;
                }
            }
            if(index != i){ // 说明位置发生变动
                move(arr,index,i);
            }
        }
        return arr;

    }

    public static int[] bubbleSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {

            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]){
                    move(arr, j, j+1);
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {

        int[] a = {2,6,3,5,7};
        int[] ints = selectSort(a);
        print(ints);
    }

}
