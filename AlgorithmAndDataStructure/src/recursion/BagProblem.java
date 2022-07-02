package recursion;

public class BagProblem {
    //背包问题版本1主函数
    public static int bagProblem(int[]v,int[] w,int bag){
        return process(v,w,0,0,bag);
    }
    /*背包问题，有两个等长数组，weights和values,对应下标值代表一个货物的重量和价值，给定一个最大载重为bag的袋子，问最多能装下多少价值的货物*/
    public static int process(int[] v,int[] w,int index,int alreadyw,int bag){
        if (alreadyw>bag){
            return -1;//如果背包里的货物已经超重，现在这个index的方案就是无效的
        }
        if (index==w.length){//如果当前的索引已经越界，即现在这是一种有效方案，虽然装进袋子里的重量为0；
            return 0;
        }
        int p1 = process(v,w,index+1,alreadyw,bag);//不要当前的货物的价值
        int p2next = process(v,w,index+1,alreadyw+w[index],bag);//要当前货物的价值
        int p2=-1;
        if (p2next!=-1){//判断一个方案有没有效果
            p2=v[index]+p2next;//有效的方案价值才会更新
        }
        return Math.max(p1,p2);
    }

    //经典写法的主函数
    public static int bag2(int[] v,int[] w,int index,int bag){
        return process2(v,w,0,bag);
    }
    //背包问题经典写法
    public static int process2(int[] v,int[] w,int index,int rest){
        if (rest<=0){
            return -1;
        }
        if (index== w.length){
            return 0;
        }
        int p1 = process2(v,w,index+1,rest);//不要当前的价值
        int p2=-1;
        int p2next = process2(v,w,index+1,rest-w[index]);
        if (p2next!=-1){
            p2=v[index]+p2next;
        }
        return Math.max(p1,p2);
    }

    public static int dp2(int[] v,int[] w,int bag){
        int[][] dp =new int[v.length+1][v.length+1];//先声明一个NxN的二维动态表
        return 0;
    }
}
