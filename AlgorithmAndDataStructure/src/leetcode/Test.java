package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        System.out.println(minStack.getMin());
//        minStack.pop();
//        System.out.println(minStack.getMin());
        long t1 = System.currentTimeMillis();
//        int res = dp(10);
        long t2 = System.currentTimeMillis();
//        System.out.println(res);
//        System.out.println(t2-t1);
//        System.out.println(isTrue(45));
//        int muti = 1;
//        for (int i=1;i<Integer.MAX_VALUE;i++){
//            if (muti*3<Integer.MAX_VALUE){
//                muti*=3;
//            }
//            else {
//                break;
//            }
//        }
//        System.out.println(954437177 %3);
//        System.out.println(isAnagram("aabb","bbaa"));

    }

    public static boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())
            return false;
        int[] check= new int[26];
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        for(int i=0;i<s1.length;i++){
            check[s1[i]-'a'+0]+=1;
            check[t1[i]-'a'+0]-=1;
        }

        int sum = 0;
        for(int i=0;i<check.length;i++){
            sum+=check[i];
        }
        if(sum==0)
            return true;
        else
            return false;
    }

    public static int test(int n){
        if(n <=2) return 0;
        int result =1;
        for( int i=3;i<n;i++){
            if(process(i)) result++;
        }
        return result;
    }

    public static boolean process( int n){
        int[] dp = new int[n];
        for (int i=2;i<=Math.pow(n,0.5);i++){
            if (n%i==0)
                return false;
        }
        return true;
    }

//    dp

//    public static int dp(int n){
//        int[] dp = new int[n];
//        process2(n,dp);
//        return dp[n-1];
//    }
//    public static void process2(int n,int[] dp){
//        if (n<=2){
//            return;
//        }
//        if (n==3){
//            dp[n-1]=1;
//            return;
//        }
//        if (process(n-1))
//            dp[n-1] = dp[n-2]+1;
//        else
//            dp[n-1] = dp[n-2];
//        dp(n-1);
//    }

    public static boolean isTrue( int n){
        if (n!=0 && 954437177 % n ==0)
            return true;
        return false;
    }

    public int t1(int[] nums){
        HashMap<Integer,Integer> map = new HashMap<>();
        int max=nums[0];
        int count=0;
        for (int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],1);
            }else{
                int tmp = map.get(nums[i]);
                if (count<tmp+1){
                     count=tmp+1;
                     max = nums[i];
                }
                map.put(nums[i],tmp+1);
            }
        }
        return max;
    }



}

