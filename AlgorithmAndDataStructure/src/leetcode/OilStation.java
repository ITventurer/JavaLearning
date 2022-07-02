package leetcode;

import java.util.ArrayList;
import java.util.List;

public class OilStation {
    public static void main(String[] args) {

    }
    //134,加油站，唯一性，renOil+gas<cost,那么中间的加油站任一点出发都不可能绕一圈
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;//尝试作为出发的最大值
        int i = 0; //起点
        while(i<n){
            int sumGas = 0;//总油量
            int sumCost= 0;//总消耗量
            int count=0;//走过了几个加油站,到达下一个才算走过，有可能一开始就走不过去
            while(count<n){//判断一共走过了多少个加油站，一圈嘛，只要走过n-1个就算成功
                int j =(i+count)%n;//环形
                sumGas+=gas[j];
                sumCost+=cost[j];
                if (sumCost>sumGas){
                    break;
                }
                count++;
            }

            if (count==n){
                return i;
            }
            else {
                i+=count+1;
            }
        }
        return -1;
    }


}
