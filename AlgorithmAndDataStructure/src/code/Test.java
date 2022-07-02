package code;
import javax.imageio.IIOException;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class Test {

    public static void main(String[] args) {
//        int[] arr = {1,8,6,2,5,4,8,3,7,10,18,16,4,20};
//        System.out.println(maxrea(arr));
//        System.out.println(maxArea(arr));
//        int a  = 100;
//        System.out.println(String.valueOf(a));
        String str = "Marge, let's \"[went].\" I await {news} telegram.";
        System.out.println(isPalindrome(str));
//        char c ='{';
//        System.out.println(c-'a');
    }

    public static int pow(int x){

        return 0;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for(String str : wordDict){
            set.add(str);
        }
        boolean[] dp = new boolean[s.length()+1];
        dp[0]=true;//目标为空字符串，肯定可以拼接成
        for(int i=0;i<s.length();i++){
            for(int j=0;j<i;j++){
                dp[i] = dp[j]&&set.contains(s.substring(j,i));
            }
        }
        return dp[s.length()];//最终状态
    }

    public static int maxrea(int[] height) {
        //每次贪最高的
        int res = Integer.MIN_VALUE;
        int minheight = 0;
        int left = 0;
        int right = height.length-1;
        int width = 0;
        while(left<right){
            width = right-left;
            if (height[left]<=height[right]){
                minheight = left++;
            }
            else{
                minheight=right--;
            }
            res = Math.max(width*height[minheight],res);
        }
        return res;
    }
    public static int maxArea1(int[ ] height) {
        //每次贪最高的 o(n2)
        int res = Integer.MIN_VALUE;
        int maxheight = Integer.MIN_VALUE;
        int j;
        for(int i=height.length-1;i>=height.length/2;i--){
            for (j=0;j<i;j++) {
                maxheight = Math.max(height[j],maxheight);
                res = Math.max((i-j)*Math.min(height[j],height[i]),res);
            }

        }
        return res;
    }


    public  static int up(int n){
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        for (int i=3;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    public static int bs(char[] arr ,char n){
        int L = 0;
        int R = arr.length-1;
        Arrays.sort(arr);
        int result =-1;
        while(L<=R){
            int mid = -1;
            if (L==R){
                mid =L;
            }else {
                mid = L+(R-L)>>1;
            }
            if (arr[mid] == n){
                result=mid;
                break;
            }
            else if(arr[mid]>n){
                R=mid-1;
            }
            else{
                L=mid+1;
            }
        }
        return result;
    }

    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        HashMap<Integer,ListNode> map = new HashMap<>();
        int count =0;
        ListNode cur=head;
        int index=2;
        while(cur!=null&&cur.next!=null){
            map.put(index,new ListNode(cur.val));
            cur =cur.next.next;
            index+=2;
            count++;
        }
        cur =head.next;
        index=1;
        while(cur!=null){
            map.put(index,new ListNode(cur.val));
            cur=cur.next.next;
            index+=2;
            count++;
        }
        head = map.get(1);
        cur=head;
        index=2;
        while(index<=count){
            cur.next = map.get(index++);
            cur=cur.next;
        }
        return head;
    }
    public static boolean isValid(String s) {
        char[] arr = s.toCharArray();
        int [] count =new int[6];
        if (arr.length==0||arr.length%2!=0){
            return false;
        }
        int index=0;
        if (arr[0]=='('&&arr[1]==')'||arr[0]=='['&&arr[1]==']'||arr[0]=='{'&&arr[1]=='}'){
            while(index+1<=arr.length-1){
                if (arr[index]=='('&&arr[index+1]!=')'||arr[index]=='['&&arr[index+1]!=']'||arr[index
                        ]=='{'&&arr[index+1]!='}'){
                    return false;
                }
                index+=2;
            }
        }
        else{
            while(index<arr.length/2){
                if (arr[index]=='('&&arr[arr.length-1-index]!=')'||
                arr[index]=='['&&arr[arr.length-1-index]!=']'||
                arr[index]=='{'&&arr[arr.length-1-index]!='}'){
                    return false;
                }
                index++;
            }
        }
        return true;
    }

    public static int getArr(int num){
        int sign=0;
        if (num<0){
            sign=1;
            num =Math.abs(num);
        }
        int count=0;
        int val=num;
        while(val>0){
            val/=10;
            count++;
        }
        int [] res = new int[count];
        int index=0;
        while (num>0){
            int b = num%10;
            num/=10;
            res[index++]=b;
        }
        int size = res.length-1;
        int newnum=0;
       for (int i=0;i< res.length;i++,size--){
           newnum+=res[i]*Math.pow(10,size);
       }

       if (sign==1){
           String str = String.valueOf(newnum);
           String signs = "-";
           newnum=Integer.parseInt(signs+str);
       }
       return newnum;
    }
    public static int myAtoi(String s) {
        char[] arr= s.toCharArray();
        int sign=0;
        String numstr="";
        int res=0;
        HashMap<Integer,Character> map = new HashMap<>();
        int index=0;
        int i=0;
        while(i<arr.length){
            if (arr[i]==' '){
                i++;
            }
            else if (arr[i]=='-'){
                map.put(index++,arr[i++]);
            }
            else if (arr[i]-'0'>=0&&arr[i]-'0'<=9){
                map.put(index++,arr[i++]);
            }
            else{
                sign=1;
                break;}
        }
        if (sign==1&&index==0){
            return res;
        }
        else {
            String str="";
            for (int j=0;j<map.size();j++){
                str+=map.get(j);
            }
            int num=0;
            if (map.get(0)!='-') {
                 num = Math.min(Integer.parseInt(str),Integer.MAX_VALUE);
            }else{
                 num =Math.max(Integer.parseInt(str),Integer.MIN_VALUE);
            }
            return num;
        }
    }
    public static int maxArea(int[] height) {
        if(height.length==0||height.length==1){
            return 0;
        }
        int max=0;
        int res=0;
        int minhigh=0;
        for (int i=0;i<height.length;i++){
            for (int j=i;j< height.length;j++){
                minhigh=Math.min(height[i],height[j]);
                max = Math.max(max,minhigh*(j-i));
            }
        }
        return max;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        if (nums[0]>0){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        int sign=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]>=0){
                sign =i;
                break;
            }
        }
        int left=0;
        int right=sign;
        int [] help = new int[3];
        while(left<sign&&right<nums.length){
            if (nums[left]+nums[right]>=0);
        }
        return res;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null){
            return null;
        }
        int count=0;
        ListNode cur = head;
        while(cur!=null){
            cur=cur.next;
            count++;
        }
        int index=0;
        cur =head;
        while(index<(count-n)){
            cur=cur.next;
        }
        ListNode temp = cur.next.next;
        cur.next.next=null;
        cur.next = temp;
        return head;
    }
    public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public int findUnsortedSubarray(int[] nums) {
        boolean flag=true;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]>nums[i+1]){
                flag =false;
            }
        }
        if(flag =true){
            return 0;
        }
        int p1=0;
        int p2=nums.length-1;
        while(nums[p1]<=nums[p1+1]){
            p1++;
        }
        while(nums[p2]>=nums[p2-1]){
            p2--;
        }
        int min=0;
        int max =0;
        for(int i=p1;i<=p2;i++){
            if(nums[i]>max){
                max=nums[i];
            }
            if(nums[i]<min){
                min = nums[i];
            }
        }
        if(nums[p1-1]<=min&&nums[p2+1]>=max){
            return (p2-p1+1);
        }
        else{return 0;}
    }














    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int [] help = new int[nums1.length+nums2.length];
        int left=0;
        int right=0;
        int index=0;
        while(left<=nums1.length-1&&right<=nums2.length-1){
            help[index++]=nums1[left]<=nums2[right]?nums1[left++]:nums2[right++];
        }
        while(left<=nums1.length-1){
            help[index++]=nums1[left++];
        }
        while(right<=nums2.length-1){
            help[index++]=nums2[right++];
        }
        if(help.length%2!=0){
            return (double)help[(help.length-1)/2];
        }
        else{
            double a = (double) help[(help.length-1)/2];
            double b =(double) help[help.length/2];
            return (a+b)/2;
        }
    }

    public static boolean isPalindrome(String s) {
        String temp = "";
        char[] arr = s.toCharArray();
        for (char c :arr){
            if ((c>='0'&&c<='9')||(c>='a'&&c<='z')||(c>='A'&&c<='Z')){
                temp=temp+c;
            }
        }
        temp=temp.toLowerCase();
        char[] strArr=temp.toCharArray();
        int index = strArr.length-1;
        for ( int i=0;i<strArr.length/2;i++,index--){
            if (strArr[i]!=strArr[index]){
                return false;
            }
        }
        return true;
    }
}