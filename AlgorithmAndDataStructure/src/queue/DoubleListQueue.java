package queue;

import linkedList.DoubleEndList;
//双向链表实现队列
public class DoubleListQueue <T>{
    private DoubleEndList<T> queue;
    public void myQueue(){
        queue = new DoubleEndList<T>();
    }
    public void push(T value){
        queue.addFromBottom(value);//队列从尾部添加数据
    }
    public void poll(){
        queue.popFromHead();//队列从头部弹出
    }
    public boolean isEmpty(){
        boolean res=false;
        if (queue==null){
            res = true;
        }
    return res;
    }
}
