package leetcode;

import java.util.Random;

//打乱数组
public class ShuffleArray {
    class Solution {
        int[] values ;
        int[] primit ;
        public Solution(int[] nums) {
            this.values = nums;
            this.primit = nums;
        }

        public int[] reset() {
            return primit;
        }

        public int[] shuffle() {
            Random random = new Random();
            for ( int i=0;i<values.length;i++){
                swap(values,i,random.nextInt(values.length-1));
            }
            return values;
        }
        public void swap(int[] arr,int l, int r){
            int tmp = arr[l];
            arr[l]=arr[r];
            arr[r]=tmp;
        }
    }
}
