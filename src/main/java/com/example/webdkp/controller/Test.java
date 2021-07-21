package com.example.webdkp.controller;

import java.util.Arrays;

/**
 * @Author:wuyuchen
 * @Description:
 * @Date:Created in 16:22 2020/11/26
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = new int[]{18, 4, 5, 6, 3, 32, 6, 5, 7, 2, 6, 4, 1};
//        int aaa=maxprofile(arr);
//        System.out.println(aaa);
        Arrays.sort(arr);
        int[] bbb = minArry(arr, 7);
        System.out.println(Arrays.toString(bbb));
    }

    public static int maxprofile(int[] arr) {
        int m = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length - i - 1; j++) {

                int n = arr[j] - arr[i];
                if (n > m) {
                    m = n;
                }
            }
        }


        return m;
    }


    public static int[] minArry(int[] arr, int k) {
//        for(int i=0;i<arr.length;i++){
//            for(int j=0;j<arr.length-1;j++){
//                int t=0;
//                if(arr[j+1]<arr[j]){
//                    t=arr[j];
//                    arr[j]=arr[j+1];
//                    arr[j+1]=t;
//                }
//            }
//
//        }
//        System.out.println(Arrays.toString(arr));
        int[] arr1 = new int[k];
        for (int i = 0; i < k; i++) {
            arr1[i] = arr[i];
        }
        return arr1;
    }

    public static String[][] xuanzhuan(String[][] arr, int a) {
        int len=arr.length;
        String[][] temp = new String[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                temp[j][len - i - 1] = arr[i][j];
            }
        }
        return temp;
    }

}
