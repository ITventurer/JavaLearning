package dynamic_programming;
//给定一个整型数组，一个目标，数组种每一个值可以使用任意次，问：共有多少种组成目标的方式
public class Money {
    public static void main(String[] args) {
        int[] arr ={100,500,200};
        System.out.println(recursionWay(arr,1000));
        System.out.println(memoryWay(arr,1000));
        System.out.println(dpWay(arr,1000));
    }

    // 暴力递归，枚举出所有可能方法
    public static int recursionWay(int[] arr, int aim){
        return process1(arr,0,aim);
    }

    public static int process1(int[] arr ,int index,int rest){//index表示表示当前使用的数值的索引
        if (index==arr.length){
            return rest==0?1:0;//如果此时已经枚举到没有数值可用了，检查rest值是否等于0，若等于0则可用，否则不是一种可能的方法
        }
        int res =0;
        //index不用移动，arr[index]在子过程会被调用
        for (int zhang =0;zhang*arr[index]<=rest;zhang++){//从第一个数取0到可取次数开始枚举各种可能的方案
            res+=process1(arr,index+1,rest-zhang*arr[index]);
        }
        return res;
    }

    //记忆化搜索
    //只要有给递归过程，只需要申请一张缓存表，在每次递归过程返回时将值加进表里就行
    public static int memoryWay(int[] arr ,int aim){
        if(aim<=0|| arr.length<=0){
            return 0;
        }
        int[][] dp = new int[arr.length+1][aim+1];//index范围0-n
        for (int i=0;i< dp.length;i++)
        {
            for (int j=0;j<dp[0].length;j++){
                dp[i][j]=-1;//新建的dp表的初始值为-1；
            }
        }
        return process2(arr,aim,0,dp);
    }

    public static int process2(int[] arr,int rest,int index,int[][] dp){
        if (dp[index][rest]!=-1){
            return dp[index][rest];//如果表里有，直接从表里拿
        }
        if (index==arr.length){
            dp[index][rest]=rest==0?1:0;//返回之前，先加缓存再返回
            return rest==0?1:0;//如果此时已经枚举到没有数值可用了，检查rest值是否等于0，若等于0则可用，否则不是一种可能的方法
        }
        int res =0;
        //index不用移动，arr[index]在子过程会被调用
        for (int zhang =0;zhang*arr[index]<=rest;zhang++){//从第一个数取0到可取次数开始枚举各种可能的方案
            res+=process2(arr,rest-zhang*arr[index],index+1,dp);//dp对整个子过程可见
        }
        dp[index][rest]=res;
        return res;
    }

    //优化的动态规划过程
    public static int dpWay(int[] arr, int aim){
        if (arr==null||arr.length==0||aim<=0) {
            return 0;
        }
        return process3(arr,aim);
    }

    public static int process3(int[] arr ,int aim){
        int n = arr.length;
        int[][] dp =new int[n+1][aim+1];//申请dp表
        dp[n][0]=1;
        for (int i=n-1;i>=0;i--){//这边可以理解，最后一层为边界条件，从边界条件逐层向上递推
            for (int rest=0;rest<=aim;rest++){//从每一层的第一个开始逐个填dp表
                dp[i][rest] =dp[i+1][rest];//下一行的同一列
                if (rest-arr[i]>=0){//判断,arr[index]指的是这一行所使用的货币大小，如果剩余所需货币大于当前行使用的货币，意味着
                    //还是可以使用当前
                    dp[i][rest]+=dp[i][rest-arr[i]];//不理解
                }
            }
        }
        return dp[0][aim];//最终的输出结果就是dp表的(0,rest)的值，这个可以根据题目推出来
    }

}
