package sort;
/*用递归和merge(合并排序两个有序数组)实现排序，递归合并排序简称归并排序。*/
//O(N*logN) O(N) 稳定
public class MergeSort {
    public static void main(String [] agrs){
        int [] arr = {4,7,3,9,2,6,3,10,11};
        mergeSort(arr);
        System.out.print("[");
        for (int nums:arr){
            if (nums==arr[arr.length-1]){
                System.out.print(nums);
            }
            else{System.out.print(nums+",");}
        }
        System.out.print("]");
    }
    /*递归过程，输入数组，输入数组左起始位置，输入数组右结束位置，然后求这两个位置的中值，base case情形位左右两个位置相等
    * 时候。递归时对左起始位置到中值的左半部分再次调用分半过程，对中值至右边的右半部分再次调用分半过程，直到触发basecase，即
    * 一个数，那肯定有序数。然后调用合并方法将两个有序数组合并为一个有序数组。1个和1个数合并，2个和2个数合并，依次返回原数组。/
     */
    public static void process(int[] arr, int L, int R) {
        int mid = L + ((R - L) >> 1);
        if (L == R) {
            return;//这边返回的是过程，表明左右索引指针相等时，process过程完成。
        }
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

   /*合并方法，首先要输入原数组，左边界，中值和右边界。创建两个指针，一个指向左部分数组起始的位置，另一个指向右部分数组起始的位置
   * 也是就中值+1处。在两个指针都不越过自己这边最大值时候，分别提取出两个指针指向的数值做比较，如果a<b，那么就把a的值保存的
   * help数组里面去，help里的指针移动个下一个待存储处。指向a的指针也移动到下一个位置处，一直执行到某个指针越界，因为每次只移动
   * 一个指针，所以一定只有一个指针越界。因为两个都是有序数组，剩下的没有越界指针处及后面的值直接刷到help数组里面去,最后把help数组
   * 里面存储的值全部刷进原数组里面去，刷进原数组的起始位置要保证和原数组要排序的起始位置相同。*/
    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int i = 0;
        while (p1 <= M && p2 <= R) {
            help[i++] = (arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++]);
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L+i] = help[i];//确保刷进原数组的位置与截取的位置一致
        }
    }

    //不用递归方式实现合并排序。
    public static void  mergeSort(int[] arr){
        /*解题思路，首先找到划分左右数组的方法，然后调用上面的合并排序方法,首先1个数合并成一个有序数组，再然后把2个2元素
        的有序数组合并成一个4元素的有序数组，以此类推最后把两个N/2元素的有序数组合并成一个N元素的有序数组，完成排序/
         */
        if (arr==null || arr.length<2){
            return;//返回类型为void时，函数返回值为void类型时，直接return表示的是什么都没有执行，即没有对原数组做操作。
        }
        int N = arr.length;//数据容量
        int mergeSize = 1;//每一次划分数组的元素容量大小
        while (mergeSize<N){
            int L =0;//左指针
            while (L<N ){//没有指到数组边界
                int M = L + mergeSize-1;
                if (M>=N){//中值指针右边没有东西了，不需要再执行合并排序操作了
                    break;//如果中间指针越过数据容量时，退出循环。
                }
                int R = Math.min(M+mergeSize, arr.length-1);//假设到最后一次，左边的数据多余右边的数据，那边M靠右，M+mergeSieze
                                                    //会超过数组长度，只需要要合并到数组的右边界即可。
                merge(arr,L,M,R);
                L = R+1;//每一次合并完成后，左指针移动到右边指针下一个位置处
            }
            if (mergeSize>N>>1){
                break;//因为合并大小每次都为翻一翻，也就是要乘以2，如果N的数据量接近int类型存储上限，那么mergeSize在一下步
                    //乘以2时可能会报内存溢出错误，可以提前判断这个条件结束循环
            }
            mergeSize <<=1;
        }



    }

}
