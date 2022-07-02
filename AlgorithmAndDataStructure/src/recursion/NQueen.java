package recursion;

import java.util.Date;

//n皇后问题，在nxn的方格上，既不能共行，也不能共列，也不能共斜线，如果存在解，请求出有多少种放法。
//8皇后,92种
public class NQueen {
    public static void main(String[] args) {
       long t1= System.currentTimeMillis();
        System.out.println(nQueen(13));
        System.out.println(System.currentTimeMillis()-t1);
    }

    public static int nQueen(int n){
        int[] record = new int[n];
        return process1(record,0,n);
    }
    //递归+回溯
    public static int process1(int[] record,int index,int n){
        if (index==n){//base case ，record[]数组的0---(n-1)数组代表行数，当行数来到第n行的时候，已经执行完毕了，肯定是一种可能的方法
            return 1;
        }
        int res=0;
        for (int j =0;j<n;j++){//j代表列数，具体一点，就是在第一行把皇后从第列放到第第列，底下判断每一个位置方案的可能性；
            if (isValid(record,index,j)){
                record[index]=j;//如果都符合要求，那么就把这个位置的皇后记录
                res+=process1(record,index+1,n);//去递归下一行
                //如果说可以沿着出发的这个点一直走到尾巴,那就是一种可能性，
                //如果在某行方案失败，那就不执行这个方案，但是还是会遍历所有的列，！！！优化点
            }//每一列都要检验是否有同行或者同列或者同斜线的情况
        }
        return res;
    }

    public static boolean isValid(int[] record, int index,int j){//记录皇后位置的数组，检查的是现在的行数，当前的列数
        //要检查之前的每个皇后是否和当前皇后重列
        for (int i=0;i<index;i++){//0-index-1这些行有没有与当前的 (index,j)冲突的皇后
            if (record[i]==j||Math.abs(record[i]-j)==Math.abs(i-index)){
                return false;
            }
        }
        return true;
    }
}
