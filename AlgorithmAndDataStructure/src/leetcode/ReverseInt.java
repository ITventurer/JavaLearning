package leetcode;

import com.sun.deploy.security.SelectableSecurityManager;

import java.util.Arrays;

//给你一个32位有符号整数，返回反转后的数字，如果越界，就返回0
public class ReverseInt {
    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE-10);
        System.out.println(Integer.MIN_VALUE);
//        System.out.println(String.valueOf(20).toCharArray()[1]);
//        System.out.println(way2(Integer.MIN_VALUE));
        System.out.println(way2(-1463847412));
    }
    //暴力超时
    public static int reverseInt(int x){
        char[] arr = String.valueOf(x).toCharArray();
        int res =0;
        char[] border=String.valueOf(Integer.MAX_VALUE).toCharArray();
        if (x<0){
            char[] help = new char[arr.length-1];
            int index=0;
            for (int i=arr.length-1;i>=1;i--,index++){
                help[index]=arr[i];
            }
            if (help.length>border.length)
            {
                return res;
            }
            else if(help.length==border.length)
            {
                boolean sign = false;
                int i =0;
                while(i<border.length)
                {
                    if (border[i]-help[i]>0)
                    {
                        sign=true;
                        break;
                    }
                    else if (border[i]-help[i]==0)
                    {
                        index++;
                        if (index== border.length)
                        {
                            sign=true;
                            break;
                        }
                    }
                    else
                    {
                        return res;
                    }
                }
                if (sign)
                    {
                        String str="";
                        str+='-';
                        for (char c : help)
                        {
                            str+=c;
                        }
                        res=Integer.parseInt(str);
                   }
            }
            else
            {
                String str="";
                str+='-';
                for (char c : help){
                    str+=c;
                }
                res=Integer.parseInt(str);
            }
        }
        else
        {
            char[] help = new char[arr.length];
            int index=0;
            for (int i=arr.length-1;i>=0;i--,index++)
            {
                help[index]=arr[i];
            }
            if (help.length>border.length)
            {
                return res;
            }
            else if(help.length==border.length)
            {
                int i =0;
                boolean sign=false;
                while(i<border.length){
                    if (border[i]-help[i]>0)
                    {
                        sign=true;
                        break;
                    }
                    else if (border[i]-help[i]==0)
                    {
                        index++;
                        if (index== border.length)
                        {
                            sign=true;
                            break;
                        }
                    }
                    else
                    {
                        return res;
                    }
                }
                if (sign)
                {
                    String str="";
                    for (char c : help)
                    {
                        str+=c;
                    }
                    res=Integer.parseInt(str);
                }
            }
            else
            {
                String str="";
                for (char c : help){
                    str+=c;
                }
                res=Integer.parseInt(str);
            }
        }
        return res;
    }

    //能过的解法
    public static int way2(int x){
        boolean sign =false;
        if (x<0){
            sign=true;
        }
        int num = Math.abs(x);
        int[] arr = new int[String.valueOf(x).length()];
        int index=0;
        int count=0;
        while(num>0){
            arr[index++]=num%10;
            num/=10;
            count++;
        }
        int res=0;
        for (int i=0;i<arr.length;i++,count--){
            res+=arr[i]*Math.pow(10,count-1);
        }
        if (sign){
            String str = "-";
            res=Integer.parseInt(str+res);
        }
        if (Math.abs(res)>=Integer.MAX_VALUE){
            return 0;
        }
        return res;
    }
}
