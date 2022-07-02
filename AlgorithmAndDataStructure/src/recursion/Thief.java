package recursion;
//一个小偷，相邻2户有报警系统，如何在不被报警的状态下偷盗最大值，给定一个整型数组作为每户所能偷盗的价值
public class Thief {
    public static void main(String[] args) {
        int[] arr = {5,4,1};
        System.out.print(way1(arr));
    }
    //状态转移方程：arr[] 经典dp ，只要找到状态转移方程，就能解决问题
    public static int way1(int [] arr){
        int[] res = new int[arr.length];
        res[0]=arr[0];
        res[1]=Math.max(arr[0],arr[1]);
        int max = res[1];
        for (int i =2;i<arr.length;i++){
            res[i]=Math.max(res[i-1],res[i-2]+arr[i]);
            if (res[i]>max){
                max = res[i];
            }
        }
        return max;
    }

    public static int  way2(int[] arr ){
        return 0;
    }
}
