package code;
/*题目：给你iyge数组，其中有2个不相同的数出现的次数为奇数，其余数出现的次数为偶数，找出并打印这两个数字*/
public class FindDoubleSigleNum {
    public static void main(String args[]){
        int [ ] arr = {1,2,3,2,3,4,4,5,5,7};
        int eor =0;
        int res=0;
        for (int num:arr){
            eor^=num;
            int rightone = eor&(~eor+1);
            if ((rightone&num)!=0){
                res ^= num;
            }
        }
        System.out.print("["+res+":"+(res^eor)+"]");

    }
}
/*解题思路，利用N^0==N,N^N==0且满足交换律和结合律的特性，首先申明一个整型变量res=0，与数组中的每个
* 数做一次^运算，并将结果保存到res，假设a,b是要寻找的2个数字，最后res的值一定为a^b,因为a!=b,因此
* res！=0，那么res中必定有一位为1，那么因为是^运算，a和b在这个位置上的值必定一个为0，一个为1，才有可能
* 呈现上述为1的情况，利用寻找最右边1的方法，找到res中最右边的1保存为rightone，再设置一个eor=0与所有
* 最后符合指定位置即，与数组中每个数字与rigthone &操作后不为0的数(因为rightone指定位置为1，其余全为0
* 若一个数字与之进行&操作不全部为0,那么必定是与rigthone中1同位置处为1，这样可以筛选出来符合要求的数组
* )，然后用res进行^操作，那么一定能够
* 找到a和b其中一个，找到后与res^操作便得到另外一个值。*/