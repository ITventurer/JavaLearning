package leetcode;

import java.util.*;

//以数组形式返回两个数组的交集,有几个重复，输出几个数字,比方说有
public class SameItemsInArray {
    public static void main(String[] args) {
        int[] a1 = {1,1,2,2};
        int[] a2 = {2,2};
        int[] a3 = {4,9,8};
        int[] a4 = {4,9,5,4,9};
        System.out.println(Arrays.toString(solution(a3,a4)));
        System.out.println(Arrays.toString(doublePointer(a3,a4)));
    }
    public static int[] solution(int[] nums1,int[] nums2){
        HashMap<Integer,Integer> map1 = new HashMap<>();
        for (int i=0;i<nums1.length;i++){
            if (!map1.containsKey(nums1[i])){
                map1.put(nums1[i],1);
            }else {
                int a = map1.get(nums1[i])+1;
                map1.put(nums1[i],a);
            }
        }
        List<Integer> list = new ArrayList<>();
//        HashMap<Integer,Integer> map2 = new HashMap<>();
        for (int i=0;i< nums2.length;i++){
            if(map1.containsKey(nums2[i])){
                int num = map1.get(nums2[i]);
                if(num!=0){
                    num--;
                    map1.put(nums2[i],num);
//                    int count =map2.getOrDefault(nums2[i],0)+1;//++不会自动拆箱
                    list.add(nums2[i]);
                }
            }
        }
        int[] arr= new int[list.size()];
        for (int i =0;i<arr.length;i++){
            arr[i]=list.get(i);
        }
        Arrays.sort(arr);
        return arr;
        }

        //双指针
        public static int[] doublePointer(int[] nums1,int[] nums2){
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int pointer1 = 0;
            int pointer2=0;
            List<Integer> list = new ArrayList<>();
            while(pointer1<nums1.length && pointer2 <nums2.length){
                if (nums1[pointer1]<nums2[pointer2]){
                    pointer1++;
                }
                else if(nums1[pointer1]>nums2[pointer2])
                {
                    pointer2++;
                }
                else {
                    list.add(nums1[pointer1]);
                    pointer1++;
                    pointer2++;
                }
            }
            int[] res = new int[list.size()];
            for (int i =0;i<res.length;i++){
                res[i] = list.get(i);
            }
            return res;
        }
}
