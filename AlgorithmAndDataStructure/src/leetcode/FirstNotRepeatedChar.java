package leetcode;

import java.util.HashMap;

//返回字符串中第一个没有重复的字符的下标
public class FirstNotRepeatedChar {
    public static void main(String[] args) {
        String s ="aabcdb";
        System.out.println(solution(s));
    }

    public static int solution(String s){
        char[] arr = s.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        int[] help = new int[arr.length];
        int index=0;
        for (int i=0;i<arr.length;i++){
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], i);
            }else {
                help[map.get(arr[i])]++;
                help[i]=1;
            }
        }
        for (int i=0;i< help.length;i++){
            if (help[i]==0){
                return i;
            }
        }
        return -1;
    }
}
