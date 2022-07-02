package sort;
import java.lang.System;
//冒泡排序 O(N2) O(1),稳定
public class BubbleSort extends Object{
    public static void main(String Args []) {
        int[] data = {6, 5, 2, 8, 3, 9, 0};//initial data
        bubbleSort(data);
        for (int val:data){
            System.out.print(val+",");
        }
    }

    public static void bubbleSort(int[] arr)
    {
     for (int i=arr.length-1;i>=0;i--){
         for (int j=0;j<i;j++){
             if (arr[j]>arr[j+1]){
                 swap(arr,j,j+1);
             }
         }
     }
    }

    public static void swap(int[] arr,int l, int r){
        int temp =arr[l];
        arr[l] =arr[r];
        arr[r]=temp;
    }



}
