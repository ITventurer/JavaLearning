package recursion;

public class Winner {
    //在一列数组中，a和b两个人拿牌，只能从数组两端那一张牌，每个人都会在自己这步想法设法让对手在下一步拿的牌点数小，返回胜者的总牌数
    public static int winner(int[] arr){
        //试探2种可能性，在初始范围上我作为先手的可能性，和我作为后手的可能性
        return Math.max(f(arr,0,arr.length-1),s(arr,0, arr.length-1));
    }
    //先手和后手的概念强调的是在同一个范围里的先手和后手
    /*先手函数，在同一个范围里，如果我是先手的话，那么在下个范围里我一定是后手，第一个范围里
    * 如果我是先手，我只有两种选择，那左边或者拿右边，我做出其中一种选择后，范围就会改变
    * 把我选过的那个牌给去除掉，那么在去除这个牌的新范围里，我变成了后手，这样交替改变角色
    * 我这一步所拿的分数就是当前选择的牌的分数+新范围里面我做为后手拿的分数和*/
    public static int f(int[] arr,int l,int r){
        if (l==r){
         return arr[l];//  base case
        }
        return Math.max(
                arr[l]+s(arr,l+1,r),//两种选择，要么拿左边，要么拿右边
                arr[r]+s(arr,l,r-1)
        );
    }

    //后手函数
    /*我作为后手，如果只剩一张的这个范围上，我肯定拿不到牌，返回0分数
    */
    public static int s(int[] arr,int l,int r){
        if (l==r){
            return 0;
        }
        /*现在这个范围我是后手，那么等先手选择完毕后，在新的范围上我是先手
        新的范围有两种可能，也就是说，我作为先手有两种可能，本阶段的先手很聪明
        他一定会让我在我的先手阶段能到的牌的总分数最小，所以一定会给我一个最小和
        选择情况。*/
        return Math.min(
                f(arr,l+1,r),
                f(arr,l,r-1)
                );
    }
    //f,s可变参数为范围，dp表为一张二维表，表中一个点的坐标代表一个范围
    /**  0 1 2 3 4 5
     * 0
     * 1
     * 2
     * 3
     * 4
     * 5
     * 根据递归过程的返回值，可以看出对角线的值为边界条件，表左下半部分为无效区域，有效区域对应点依赖另一表对应点的依赖关系
     */

    public static int dpWay(int[] arr ){
        int n =arr.length;
        int[][] f = new int[n][n];//f
        int[][] s = new int[n][n];//s
        for (int i=0;i<n;i++){
            f[i][i]=arr[i];//f表边界条件,对角线值等于数组值
        }//s表边界条件对角线值为0，java默认值为0；
        for (int i=1;i<n;i++){//控制列移动
            int row = 0;//每次填充对角线
            int column = i;
            while(row<n&&column<n){
                f[row][column]=Math.max(arr[row]+s[row+1][column],arr[column]+s[row][column-1]);//依赖关系
                s[row][column]=Math.min(f[row+1][column],f[row][column-1]);//依赖关系
                row++;
                column++;
            }
        }
        return Math.max(f[0][n-1],s[0][n-1]);//返回值
    }

    public static void main(String[] args) {
        int[] arr = new int [30];
        for (int i=0;i<10;i++){
            arr[i] =(int)(Math.random()*100);
        }
        long t=System.currentTimeMillis();
        System.out.println(winner(arr));
        long t1 =System.currentTimeMillis();
        System.out.println(t1-t);
        System.out.println(dpWay(arr));
        System.out.println(System.currentTimeMillis()-t1);
    }


}
