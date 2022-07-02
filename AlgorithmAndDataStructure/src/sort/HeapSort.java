package sort;
//堆排 O(N*logN),O(1),不稳定
public class HeapSort {
    public static void main(String [] args){
        int [] arr= {2,3,1,8,3,6,9,4,7};
        heapSort(arr);
        for (int nums :arr){
            System.out.print(nums+",");
        }

    }
    /*堆排序，首先将给定数组排成一个大根堆，即遍历一遍数组，对数组的每个值左heapify操作，第一次完成后给定数组变为
    * 一个大根堆，数组第一个数为全局最大，首位交换，然后在堆大小-1的基础上再重复上述操作。*/
    public static void  heapSort(int [] arr){
        int heapsize = arr.length;
        if (arr==null||arr.length<2){
            return;
        }
        for (int i=0;i< arr.length;i++){//遍历每一个索引，对每一个索引做大根堆处理
            heapInsert(arr,i);
        }
//        int heapsize = arr.length;
        swap(arr,0,--heapsize);//大根堆首尾交换，--heapsize为数组末位索引
        while (heapsize>0){//heapsize=原先长度减一
            heapify(arr,0,heapsize);
            swap(arr,0,--heapsize);
        }

    }
    //从堆尾部开始插入值并排成大根堆
    public static void heapInsert(int[] arr,int index){
        while(arr[index]>arr[(index-1)/2]){//还只能这样写，写成其他的会报错
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }
    //堆中大值下沉函数,index的意思是从哪个位置开始下沉。
    public static void heapify(int[] arr,int index,int heapsize){
        int leftindex = 2*index+1;
        while(leftindex<heapsize){
            int largeindex = (leftindex+1<heapsize)&&arr[leftindex+1]>arr[leftindex]?leftindex+1:leftindex;
            largeindex = arr[largeindex]>arr[index]?largeindex:index;
            if (largeindex==index){
                break;
            }
            swap(arr,index,largeindex);
            index = largeindex;
            leftindex = 2*index+1;
        }

    }
    //值交换函数。
    public static void swap(int[] arr,int index1,int index2)
    {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] =temp;
    }

}
