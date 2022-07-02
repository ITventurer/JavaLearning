package leetcode;

import java.util.ArrayList;
import java.util.List;
//给定一个升序数组，找到目标值在数组中的第一个和最后一个索引，以数组形式返回，找不到返回[-1,-1]
public class SearchRangeIndex {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,2};
        System.out.println(binarySearch(arr,2));
    }
    //纯暴力解法
    public int[] searchRange(int[] nums, int target) {
        int min = nums[0];
        int max = nums[nums.length];
        int[] res = new int[2];
        res[0]=-1;
        res[1]=-1;
        if (target<min || target>max || nums.length==0)
        {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        for (int i=0;i< nums.length;i++){
            if (nums[i]==target){
                list.add(i);
            }
        }
        if (list.size()==0)
            return res;
        if (list.size()==1) {
            res[0]= list.get(0);
            res[1]=list.get(0);
        }
        else {
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            for (Integer e : list){
                if (e<min)
                    min=e;
                if (e>max)
                    max=e;
            }
            res[0]=min;
            res[1]=max;
        }
        return res;
    }

    //二分查找，只针对int类型
    public static int[] binarySearch(int[] nums,int target){
        int num1 = process(nums,target);
        int num2 = process(nums,target+1);
        int[] res = new int[2];
        res[0] = num1;
        res[1] = num2;
        return res;
    }

    //取target第一个值
    public static int process(int[] nums,int target){
        int left=0;
        int right= nums.length-1;
        int mid = (left+right)>>1;
        while (left<right){
            if (nums[mid]>=target){
                right=mid;
            }
            else {
                left=mid+1;
            }
        }
        return left;
    }
}
