package code;
/*一个数组中，只有一个数出现了奇数次，其他数都是出现了偶数次，请打印出这个数*/
/*0^N=N,N^N=0*/
public class FindSingleNum {
    public static void main(String args[]) {
        int [ ] arr ={1,3,4,1,3,4,3,3,5};
        int res = 0;
        for (int num : arr){
            res = res ^ num;
        }
        System.out.print("出现奇数次的数为："+res);

    }
}
