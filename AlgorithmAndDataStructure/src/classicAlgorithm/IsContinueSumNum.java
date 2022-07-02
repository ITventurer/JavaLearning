package classicAlgorithm;

public class IsContinueSumNum {
    /*暴力打表，寻找规律，优化代码*/
    /*给你一个正整数，若可以写成由联系的数字的和，则返回true，否则false，例:5=2+3,12=3+4+5*/
    public static void main(String[] args) {
        for (int i=1;i<100;i++){
            System.out.println(""+(i)+":"+isNum(i));
        }
    }

    public static boolean isNum(int num){
        for (int i=0;i<num;i++){
            int sum =i;
            for (int j=i+1;j<num;j++){
                sum+=j;
                if (sum==num){
                    return true;
                }
                if (sum>num){
                    break;
                }
            }
        }
        return false;
    }

    //打表技巧
    public static boolean isRightNum(int i){
        if (i<3){
            return false;
        }
        return (i&(i-1))!=0;//装逼写法 a&a-1==0，a是2的某次方，！=0则不是;
    }

    //牛和🐏是吃草，一次只能吃1，4，16(4的指数次)，两者都想获胜，问输入草总数n，牛先吃的情况下，谁会赢。
    public static class WhoWins {
        public static void main(String[] args) {
            for (int i=0;i<30;i++){
                System.out.println(whoWinsTheGamble(i));
            }
        }

        public static String whoWinsTheGamble(int n){
            if (n<5){//base case
                return (n==0||n==2)?"后手":"先手";
            }
            int base =1;//从1开始循环试吃
            while(base<=n){
                if (whoWinsTheGamble(n-base).equals("后手")){
                    return "先手";
                }
                if (base>n>>2){
                    break;
                }
                base*=4;
            }
            return "后手";
        }

        public static String winner(int n){
            if (n%5==0||n%5==2){
                return "后手";
            }else{
            return"先手";//上面打表找到的技巧
            }
        }
    }
}
