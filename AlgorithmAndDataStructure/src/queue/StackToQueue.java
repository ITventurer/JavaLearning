package queue;
import java.util.Stack;

public class StackToQueue {
    private Stack<Integer> stack;
    private Stack<Integer> queue;
    public StackToQueue(){
        this.queue = new Stack<Integer>();
        this.stack = new Stack<Integer>();
    }
    public void stackToQueue(){
        if (queue.empty()){
            while (!stack.empty()){
                queue.push(stack.pop());//循环，一个一个的全部倒完。
            }
        }
    }
    public void push(int newnum){
        this.stack.push(newnum);
        this.stackToQueue();
    }
    public int pop(){
        if (this.queue.empty() && this.stack.empty()){
            throw new RuntimeException("一滴都没有了！");
        }
        stackToQueue();
        return this.queue.pop();//如果是queue不为空的话，该怎么做呢，其实他这种倒数据是原子性操作。
    }
}
//注意点，stack中数据向queue中倒入时必须保证queue为null，且必须要一次性全部倒至queue中
/*用栈结构实现队列，因为队列时先进先出结构，而栈是先进后出结构，所有用两个栈，一个顺序为反，负负得正，两个就可以’
* 调回顺序，逻辑，首先第一个栈压入newnum,然后将第一个栈弹出的数据压入第二个栈。压入操作就是上述过程，弹出方法
* 就是第二个栈直接弹出的数字就是第一个进入的数字*/
