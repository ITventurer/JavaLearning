package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
* 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

 */
public class SubSet {
    public static void main(String[] args) {
        int[] nums ={1,2,3};
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<Integer> list =new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        int index=0;
        process(nums,set,list,index);
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> e : set){
            res.add(e);
        }
        return res;
    }
    //这边有个问题，每次传递的都是同一个list的饮用，因此相当于要不要每次都会作用在同一个list对象上，最后会导致每一个list对象的值都是一样的
    public static void process(int[] nums,HashSet<List<Integer>> set,List<Integer> list, int index){
        if (index== nums.length){
            set.add(list);
            return;
        }
        //数组当前值不要
        List<Integer> no = new ArrayList<>(list);
        process(nums,set, no,index+1);
        //数组当前值要
        List<Integer> yes = new ArrayList<>(no);
        yes.add(nums[index]);
        process(nums,set, yes,index+1);
    }
}

