package leetcode;

import java.util.HashSet;

//无重复字符的最长字符字串
public class LongestSubString {
    public static void main(String[] args) {
        String a ="abcabcbb";
        System.out.println(lengthOfLongestSubString(a));
    }
    //a="abcabcbb" ,3
    public static int lengthOfLongestSubString(String s){
        if(s==null||s.length()==0){
            return 0;
        }
        if (s.length()==1){
            return 1;
        }
        char[] arr = s.toCharArray();
        int res =0;
        for (int i=0;i<arr.length;i++){
            int total=0;
            HashSet<Character> set = new HashSet<>();
            set.add(arr[i]);
            total++;
            for (int index=i+1;index<arr.length;index++){
                if (!set.contains(arr[index])){
                    set.add(arr[index]);
                    total++;
                    res=Math.max(res,total);
                }else{
                    break;
                }
            }
        }
        return res;
    }
}
