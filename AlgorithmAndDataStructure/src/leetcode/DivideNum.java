package leetcode;
//整数相除，不允许使用*，/%
public class DivideNum {
    public static void main(String[] args) {
        int a =-2147483648;
        int b= 122481295;
//        System.out.println(Integer.MIN_VALUE);
        System.out.println(divide(a,b));

    }

    public static int divide(int dividend, int divisor) {
        boolean sign = false;
        if (dividend==Integer.MIN_VALUE && divisor==-1){
            return Integer.MAX_VALUE;
        }
        if(dividend==Integer.MIN_VALUE && divisor==1){
            return Integer.MIN_VALUE;
        }
        if(dividend==Integer.MIN_VALUE && divisor==Integer.MIN_VALUE){
            return 1;
        }
        if( divisor==Integer.MIN_VALUE){
            return 0;
        }
        boolean flag =false;
        if(dividend==Integer.MIN_VALUE){
            flag=true;
        }
        if(dividend <0 && divisor >0 || divisor<0 && dividend >0){
            sign =true;
        }

        if(flag){
            dividend=Integer.MAX_VALUE;
        }
        else
        {
            dividend=Math.abs(dividend);
        }
        divisor=Math.abs(divisor);

        int count =0;
        if(dividend-divisor<0){
            return 0;
        }
        while(dividend >divisor){
            dividend-=divisor;
            count++;
        }
        if (dividend==divisor){
            count++;
        }
        if(flag){
            if(!sign){
                count++;
            }
        }
        if (sign){
            count =Integer.parseInt("-"+count);
        }
        return count;
    }
}
