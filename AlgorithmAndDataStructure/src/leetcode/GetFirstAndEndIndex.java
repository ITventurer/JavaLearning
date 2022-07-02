package leetcode;

import java.util.Arrays;

//给定一个升序数组和一个目标值，请你找出目标值的起始和结束坐标，若不存在，返回-1
public class GetFirstAndEndIndex {
    public static void main(String[] args) {
        int[] arr = {0,0,1,1,1,2};
        int target = 2;
        System.out.println(process(arr,target,0,arr.length-1));
        System.out.println(Arrays.toString(getIndex(arr,target)));
    }

    //二分查找，找到后，前后遍历比较目标值，返回一个数组
    public static int[] getIndex(int[] nums,int target){
        int[] res = new int[2];
        res[0]=-1;
        res[1]=-1;
;        int min = nums[0];
        int max = nums[nums.length-1];
        if (target<min || target >max){
            return res;
        }
        int index=process(nums,target,0,nums.length-1);
        if (index==-1){
            return res;
        }
        int p1=index;
        int p2=index;
        while(p1>=0){
            if (nums[p1]<target){
                break;
            }
            p1--;

        }
        while(p2< nums.length){
            if(nums[p2]>target){
                break;
            }
            p2++;
        }
        res[0]=p1+1;
        res[1]=p2-1;
        return res;
    }

    public static int  process(int[] arr,int target,int left,int right){
        if (left>right){
            return -1;
        }
        int mid =left+((right-left)>>1);
        if (arr[mid]<target){
            return process(arr,target,mid+1,right);
        }
        else if (arr[mid]>target){
            return process(arr,target,left,mid-1);
        }
        else{
            return mid;
        }
    }

}
