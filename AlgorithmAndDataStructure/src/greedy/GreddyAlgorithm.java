package greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

//贪心算法题目
public class GreddyAlgorithm {
    public static void main(String[] args) {

    }

    //放灯题，住户放灯，墙放不了灯，一盏灯可以点亮左右各一个位置，问至少需要放多少才能全部点亮。'0'墙'.'住户
    public static int getLeastLights(String road){
        char[] arr = road.toCharArray();
        int index=0;
        int lights=0;
        while(index<arr.length){
            if (arr[index]=='0'){
                index++;
            }
            else{
                lights++;
                if (index+1==arr.length){
                    break;
                }
                else {
                    if (arr[index+1]=='0'){
                        index+=2;
                    }
                    else {
                        index+=3;
                    }
                }
            }
        }
        return lights;
    }

    //哈夫曼数,切金条策略，求最小代价
    public static int lessMoney(int[] arr ){
        PriorityQueue<Integer> pQ = new PriorityQueue<>();//小根堆
        for (int i=0;i<arr.length;i++){
            pQ.add(arr[i]);//add进去就变成小根堆了
        }
        int sum=0;
        int cur=0;
        while (pQ.size()>1){
            cur = pQ.poll()+ pQ.poll();
            sum+=cur;
            pQ.add(cur);//出来2个加起来再扔进去，其实贪心策略是每一步拿出最大的数。
        }
        return sum;
    }

    //一个项目利润是p,花费是c，初始启动资金为1，最多做几个项目，问最大盈余是多少
    public static int getMaxEarned(int w,int k,int[] c,int[] p){//w启动资金，k可做项目个数，c和p对应i位置的花费和盈利
        PriorityQueue<Program> mincost = new PriorityQueue<>(new minCostComparator());//自定义排序方法//小根堆
        PriorityQueue<Program> maxProfit = new PriorityQueue<>(new maxProfitComparator());//大根堆

        for (int i=0;i<c.length;i++){
            mincost.add(new Program(p[i],c[i]));
        }
        for (int i=0;i<k;i++){
            while(!mincost.isEmpty()&&mincost.peek().c<=w){
                maxProfit.add(mincost.poll());//小根堆不为空且能够被启动的项目全部弹出并添加到大根堆中
            }
            if (maxProfit.isEmpty()){
                return w;
            }
            w+=maxProfit.poll().p;
        }
        return w;
    }

    public static class Program {
        public int p;//利润
        public int c;//花费
        public Program(int p, int c){
            this.p=p;
            this.c=c;
        }
    }

    public static class minCostComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.c- o2.c;//最经典的排序，升序
        }
    }

    public static class maxProfitComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o2.p- o1.p;//从大到小排序，降序
        }
    }
}
