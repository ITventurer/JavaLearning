package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//字符串子系列序列
public class SubStringSequence {
    public static void main(String[] args) {
        String str = "abcd";
        System.out.println(noRepeatSubString(str));
    }
    //第一版本
    public static List<String> subString(String str) {
        char[] string = str.toCharArray();
        String path = "";//沿途路径
        List<String> result = new ArrayList<>();
        process1(string, 0, result, path);
        return result;
    }

    public static void process1(char[] arr, int index, List<String> result, String path) {
        if (index == arr.length) {
            result.add(path);
            return;
        }
        String no = path;//不要当前这个字符
        process1(arr, index + 1, result, no);
        String yes = path + String.valueOf(arr[index]);//要当前这个字符
        process1(arr, index + 1, result, yes);
    }

    //无重复的字符串子序列
    public static List<String> noRepeatSubString(String str) {
        char[] string = str.toCharArray();
        HashSet<String> set = new HashSet<>();
        String path ="";
        process2(string,0,set,path);
        List<String> list = new ArrayList<String>();
        for (String cur:set){
            list.add(cur);
        }
        return list;
    }

    public static void process2(char[] arr,int index,HashSet<String> set,String path){
        if (index==arr.length){
            set.add(path);
            return;
        }
        String no =path;
        process2(arr,index+1,set,no);
        String yes =path+String.valueOf(arr[index]);
        process2(arr,index+1,set,yes);
    }

    //字符串全排列,去重的话就是用hashset去重
    public static List<String> allPermutation(String str){
        List<String> list =new ArrayList<>();
        if (str.length()==0||str==null){
            return list;
        }
        char[] arr = str.toCharArray();
        process3(arr,0,list);
        return list;
    }
    //对于字符串中的i位置,每一个此位置及其后面的位置的值都有机会来到当前位置
    public static void process3(char[] str,int index,List<String> list){
        if (index==str.length){
            list.add(String.valueOf(str));
            return;
        }
        for (int j=index;j<str.length;j++){
            swap(str,j,index);//j位置的值来到index位置
            process3(str,index+1,list);//index前进
            swap(str,index,j);//复原现场
        }
    }

    public static void swap(char[] arr ,int l, int r){
        char temp = arr[l];
        arr[l]=arr[r];
        arr[r]=temp;
    }

    //分支限界去除重复的全排列字串
    public static void process4(char[] arr,int index,List<String> res){
        if (index==arr.length){
            res.add(String.valueOf(arr));
            return;
        }
        Boolean[] visit = new Boolean[26];
        for (int j=index;j<arr.length;j++){
            if (!visit[arr[j]-'a']){
                visit[arr[j]-'a']=true;
                swap(arr,j,index);
                process4(arr,index+1,res);
                swap(arr,index,j);
            }
        }
    }
}