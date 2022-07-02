package heap;
//堆结构和算法
public class Heap {
    public static class MyMaxHeap
    {
        private int[] heap;
        public int heapsize;
        private final int limit;

        public MyMaxHeap( int limit)
        {
            heap = new int[limit];
            this.limit = limit;
            heapsize = 0;
        }
        public static void swap ( int[] arr, int L, int R)
        {
            int temp;
            temp = arr[R];
            arr[R] = arr[L];
             arr[L] = temp;
        }

        public void heapInsert ( int[] arr, int index)
        {//堆结构默认从数组尾部添加值
        if (heapsize == limit)
        {
            throw new RuntimeException("heap is full");
            }
        while (arr[index] > arr[(index - 1) / 2])
        {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
            }
        }

        public int pop ()//删除最大值并返回
        {
            int res = heap[0];
            swap(heap, 0, --heapsize);
            heapify(heap,0,heapsize);
            return res;
        }
        public void heapify(int [] arr,int index,int heapsize)
        {
            /*如果右节点存在，且右节点的值大于左节点的值，把右节点的索引赋给大值索引，若
           右边值不存在或者左边值大，就把左索引赋给大值索引，如果当前值和大值相等，那么退出循环
            ，如果不是，则交换两个索引的值，并更新当前索引值为最大值索引，更新左子节点值为当前索引的子节点值，直到左子节点
            越界，脱出循环。*/
            int leftindex = index*2+1;//左子节点索引值
            while (leftindex<heapsize){
                int largeindex = (leftindex+1<heapsize)&&arr[leftindex+1]>arr[leftindex]? leftindex+1:leftindex;
                largeindex = arr[largeindex]>arr[index]?largeindex:index;//largeindex更新为当前值和大值节点值哪个更大的索引.
                if (index==largeindex){
                    break;
                }
                swap(arr,index,largeindex);
                index=largeindex;
                leftindex = index*2+1;
            }

        }

    }
}
