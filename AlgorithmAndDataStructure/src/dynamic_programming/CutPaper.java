package dynamic_programming;

import java.util.Arrays;

//最少贴纸问题
public class CutPaper {
    /*给定一个字符串str="aaabcd",一个字符串数组arr={"ab","ac","cd"}，对于数组中的每一个视为一张贴纸，每一种贴纸数量无限，每一个贴纸都可以剪碎成
    * 一个字符串，问至少需要多少张贴纸才可以将str贴满*/
    public static void main(String[] args) {

    }

    //暴力枚举,试第一个用谁
    public static int recursionWay(String[] arr ,String aim){
        if (aim==null||arr==null||arr.length==0){
            return 0;
        }
        char[] chararr = aim.toCharArray();
        Arrays.sort(chararr);
        return process1(arr,0,chararr);
    }

    public static int process1(String[] arr ,int index,char[] aim){
        if (aim.length==0){
            return 1;
        }
        if (index==arr.length){
            return 0;
        }
        int res = Integer.MAX_VALUE;
        boolean flag =false;
        for (int i=0;i<arr.length;i++){

        }
        return res;
    }
}
