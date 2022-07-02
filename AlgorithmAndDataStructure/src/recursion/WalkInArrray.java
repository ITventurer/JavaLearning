package recursion;
/*有一个序列1-N,规则，如果你在1位置，那么只能向右边走到2位置，如果你在N位置，只能走向N-1位置，如果在中间，既可以向左走又可以向右走*/
public class WalkInArrray {
    public static void main(String[] args) {
        System.out.println(getAllWays(10, 3, 2));
        System.out.println(ways(10,3,2));
    }
    //改进方法，动态规划--记忆化搜索，增加缓存，保存计算过的结果，减少重复计算--后期自己优化
    //机器人问题
    public static int getAllWays(int N,int rest,int desti){
        return walk(N,1,rest,desti);
    }
    //N代表1-N位置，cur当前人所在的位置，rest剩余可走步数，destination目的地
    public static int  walk(int N, int cur,int rest,int destination){
        if (rest==0){
            return cur==destination?1:0;//没可走步数了，判断下是不是在目的地，如果是，这是一种可能的方案
        }
        if (cur==1){
            return walk(N,2,rest-1,destination);//来到了1，下一步没得选只能去到第二个位置
        }
        if (cur==N){
            return walk(N,N-1,rest-1,destination);//如果说来到了最后面，下一步只能去n-1位置上
        }
        return walk(N,cur-1,rest-1,destination)+walk(N,cur+1,rest-1,destination);
    }   //在中间，那么有两种走法，一向左走一共有多少方案，向右走一种有多少种方案，两者之和就是总的走法;

    public static int dpWay(){
        return 0;
    }



    //机器人问题字写,1-N位置，如果在1或者在N只能往旁边走，在中间可以任意选择向左或者向右

    public static int ways(int N,int rest ,int aim){
        return process(N,1,rest,aim);
    }

    public static int process(int N,int cur,int rest,int aim){
        //边界条件，如果是rest==0了，判断一下当前位置是不是目标，如果是的话，就确认为一个结果，
        if (rest==0){
            return cur==aim?1:0;
        }
        if (cur==1){
            return process(N,2,rest-1,aim);
        }
        if (cur==N){
            return process(N,N-1,rest-1,aim);
        }
        return process(N,cur-1,rest-1,aim)+process(N, cur+1, rest-1, aim);
    }
}