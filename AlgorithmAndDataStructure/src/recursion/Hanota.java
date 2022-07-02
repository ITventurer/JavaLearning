package recursion;

import java.util.Stack;

//汉诺塔问题，左中右三根柱子，最左边柱子上由上至下套着直径从小到大的盘子，移动规则：只能小盘压大盘或放空柱子上，目标把盘子移动到最右边柱子上
public class Hanota {

    //移动过程，from移动盘子的起点柱子，to移动盘子的目标柱子，other 另外一根空柱子
    public static void main(String[] args) {
        hanota(4);
    }
    //汉诺塔问题
    //n层最优次数一定是2^n-1步
    public static void hanota(int n){
        process(n,"左柱子","右柱子","中间柱子");
    }

    public static void process(int n, String from, String to , String other){
        if (n==1){
            System.out.println("把第1个盘子从" +from+"移动到"+to);
        }
        else{
            process(n-1,from,other,to);//给第n个盘子腾路，给我把n-1个盘子从起点移动到助力柱子上去
            System.out.println("把第"+n+"个盘子从"+from+"移动到"+to);//第n个盘子移动到目标点去
            process(n-1,other,to,from);//好了，最后一个盘子已经顺利移动到目标柱子上去了，下一步给我以助力柱子为起点
            //给我把n-1个盘子移动到目标柱子上去，原先的起点现在成为助力柱子
        }
    }

}
