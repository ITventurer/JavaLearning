package stack;

import linkedList.DoubleEndList;

//用双链表实现栈
public class DoubleListStack <T>{
    private DoubleEndList<T> queue;
    public void mystack(){
        queue = new DoubleEndList<T>();
    }
    public void push(T value){
        queue.addFromBottom(value);//栈方法从尾部添加元素
    }
    public void pop(){
        queue.popFromHead();//栈从头部弹出
    }
    public boolean isEmpty(){
        boolean res =false;
        if (queue==null){
            res =true;
        }
        return res;
    }
}

