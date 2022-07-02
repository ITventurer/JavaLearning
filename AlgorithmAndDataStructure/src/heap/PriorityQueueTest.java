package heap;
import java.util.PriorityQueue;
//priorityQueue 就是java内置的堆结构，默认小根堆，大根堆需要传入一个倒序的比较器
public class PriorityQueueTest {
    public static void main(String[] args){
        int [] arr = {2,3,7,4,9,7,5,7,3,2,1};
        sortedArrLessDistanceK(arr,6);
        for (int i =0;i<arr.length;i++){
            System.out.print(arr[i]+",");
        }
    }
    public static void sortedArrLessDistanceK(int[] arr,int k){
        PriorityQueue<Integer> heap = new PriorityQueue<>();//小根堆
        int index =0;//定义在外面后面的循环最后一个值会更新进去
        for (;index<=Math.min(arr.length-1,k);index++) {
            heap.add(arr[index]);
        }
        int i=0;
        for (;index< arr.length;i++,index++){
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while(!heap.isEmpty()){
            arr[i++] =heap.poll();
        }
    }
}
