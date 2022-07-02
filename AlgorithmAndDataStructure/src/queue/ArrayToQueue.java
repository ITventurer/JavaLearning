package queue;
//固定大小数组实现队列
public class ArrayToQueue {
    private int pushindex=0;
    private int pollindex=0;
    private int size=0;
    private int [] arr;
    private final int limit;
    public ArrayToQueue(int limit){
        this.limit = limit;//输入数组的大小
        arr= new int[limit];
    }
    public void push(int value){
        if (size==limit){
            throw new RuntimeException("队列已经满了，不能再加了");
        }
        size++;
        arr[pushindex] = value;
        pushindex = nextIndex(pushindex);
    }
    public int poll(){
        if (size==0){
            throw new RuntimeException("一滴都没有了");
        }
        size--;
        int result = arr[pollindex];
        pollindex = nextIndex(pollindex);
        return  result;
    }
    public int nextIndex(int index){
        if (index<size-1){
            index +=1;
        }
        else {
            index =0;
        }
        return index;
    }
}
/*固定数组实现队列核心思想是创建一个指向待添加位置的指针，创建一个指向取出位置的指针，创建一个容量变量，循环利用
* 数组存储空间。主要逻辑为，如果容量为空或者为满时，抛出容量异常。在取值时，指针从第一个添加的索引0出发依次取值，
* 每取出一个值，容量大小减一，指针指向下一个待取索引。在添加一个值时，从添加指针处添加一个值，容量大小加一，并将添加
* 指针指向下一个待添加出。循环往复，保证队列先进先出的顺序。假设场景，数组已经满了，添加指针指向0，取出指针也指向0，
* 因为容量大小等于数组大小，所以会抛出异常。双指针构成一个环状结构，先进先出的顺序得到保证。*/