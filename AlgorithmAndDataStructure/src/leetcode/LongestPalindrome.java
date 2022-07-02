package leetcode;

import java.util.HashMap;

//最长回文字串,s = "babad,"bab"
public class LongestPalindrome {
    public static void main(String[] args) {
        String a = "";
        System.out.println(longestPalindrome(a));
//        System.out.println(a.substring(1,2));
    }

    //暴力超时,截取字串优化为一个loop,还是超时
    public static String longestPalindrome(String s) {
        if (s==null || s.length()==0 || s.length()==1){
            return s;
        }
        String res="";
        int window = s.length();
        while(window>0){
            if (window==1){
                return s.substring(0,1);
            }
            int i =0;
            while(i+window<=s.length()){
                if (isPalindrome(s.substring(i,i+window))){
                    res+=s.substring(i,i+window);
                    break;
                }
                i++;
            }
            if (i<=s.length()-window){
                break;
            }
            window--;
        }
        return res;
    }

    public static boolean isPalindrome(String s){
        char[] arr = s.toCharArray();
        boolean res= true;
        if(arr.length==0||arr.length==1){
            return res;
        }
        int index=0;
        while(index<arr.length/2){
            if (arr[index]!=arr[arr.length-1-index]){
                res=false;
                break;
            }
            index++;
        }
        return res;
    }
}
