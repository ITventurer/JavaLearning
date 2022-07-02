package sort;
//快排 O(N*logN),O(1),不稳定
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 6, 3, 8, 4, 9, 2, 7, 3, 7, 4, 7};
        quickSort2(arr, 0, arr.length - 1);
        for (int nums : arr) {
            System.out.print(nums + ",");
        }

    }

    //第一个问题，给的一个数组和一个数，要求将数组中小于等于这个数的元素放在数组左边，大于这个数的元素放数组右边
    //空间复杂度为0(1)，时间复杂的为o(n);
    public static void leftNumRight(int[] arr, int n) {
        int p = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < n) {
                int temp = arr[i];
                arr[i] = arr[p];
                arr[p] = temp;
                p++;
            }
        }
    }

    //荷兰国旗问题
    public static void netherLandFlag(int[] arr, int n) {
        int pmin = -1;//小于区域第一个数指针；
        int pmax = arr.length - 1;//大于区域左一个数指针；
        int i = 0;
        while (i < pmax) {
            if (arr[i] < n) {
                swap(arr, i++, ++pmin);
            } else if (arr[i] > n) {
                swap(arr, i, pmax--);
            } else {
                i++;
            }
        }
    }

    //荷兰国旗问题广义写法,以有边界数为划分值
    public static void netherLandFlagQuestion(int[] arr, int L, int R) {
        if (L > R) {
            System.out.print("输入参数边界参数有误！");
            return;
        }
        if (L == R) {
            return;
        }
        int left = L - 1;
        int right = R;
        int i = L;
        while (i < right) {
            if (arr[i] == arr[R]) {
                i++;
            } else if (arr[i] > arr[R]) {
                swap(arr, i, --right);
            } else {
                swap(arr, i++, ++left);
            }
        }
        swap(arr, right, R);
    }

    //swap交换数组元素位置函数
    public static void swap(int[] arr, int index1, int index2) {
        int temp;
        temp = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = temp;
    }

    //快排1.0版本
    public static void quickSort1(int[] arr, int L, int R) {
        if (L > R || L == R) {
            return;
        }
        int mid = partition1(arr, L, R);
        quickSort1(arr, L, mid - 1);
        quickSort1(arr, mid + 1, R);
    }

    //partition 方法，以数组最后一个数为划分点，经过分区处理后返回该值的索引
    public static int partition1(int[] arr, int L, int R) {
        int min = L - 1;
        int max = R;
        int index = L;
        while (index < max) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++min);//确保经过partition方法调用后，R位置的值放在排序后对应的位置
            } else {
                swap(arr, index, --max);
            }
        }
        swap(arr, R, max);
        return max;
    }

    //快排2.0版本，与值对应的一批数位置都固定了，两边划分时可以把这个区域给划掉
    public static void quickSort2(int [] arr,int L, int R){
        if (L>=R){
            return;
        }
        int [] midrange = partition2( arr, L, R);
        quickSort2(arr,L,midrange[0]-1);
        quickSort2(arr,midrange[1]+1,R);
    }

    public static int[] partition2(int[] arr,int L,int R){
        int min = L-1;
        int max = R;
        int[] result=new int[2];
        int index = L;
        while(index<max){
            if (arr[index]==arr[R]){
                index++;
            }
            else if (arr[index]<arr[R]){
                swap(arr,index++,++min);
            }
            else {
                swap(arr,index,--max);
            }
        }
        swap(arr,max,R);
        result[0]=min+1;
        result[1] = max;
        return result;
    }

    //快排3.0版本
    //相比于之前的两个版本中拿划分数组的最后一个数作为划分值，3.0版本采用随机划分值方法，最终时间复杂度收敛于NlogN，空间复杂度logN
    public static void quickSort3(int[] arr,int L,int R){
        if (L>=R){
            return;
        }
        swap(arr,L+(int)Math.random()*(R-L+1),R);//因为partition每次按划分数组最后一个值作比较，所以每次要将R位置的值与一个随机位置的值做交换
        int[] result = partition2(arr,L,R);
        quickSort3(arr,L,result[0]-1);
        quickSort3(arr,result[1]+1, R);

    }
}
//不需要排序，只需要将大于给定数的元素和小于给定数的元素分别归为与数组的左右两边就行

