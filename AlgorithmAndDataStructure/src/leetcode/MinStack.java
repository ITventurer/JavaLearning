package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
/*
* 实现 MinStack 类:

MinStack() 初始化堆栈对象。
void push(int val) 将元素val推入堆栈。
void pop() 删除堆栈顶部的元素。
int top() 获取堆栈顶部的元素。
int getMin() 获取堆栈中的最小元素。
* */
public class MinStack {
    List<Integer> stack ;
    HashMap<String,Integer> map  =new HashMap<>();
    int minElement=Integer.MAX_VALUE;
    int size =0;
    String before_min = "last";
    String thisMIn = "this";
    public MinStack() {
        this.stack= new ArrayList<>();
    }

    public void push(int val) {
        if (val<minElement){
            stack.add(val);
            size++;
            map.put(before_min, size-1);
        }
        else {
            stack.add(val);
            size++;
        }
    }

    public void pop() {
        if (size ==0){
            System.out.println("没有元素删除");
        }
        else{
            if (size-1==map.get(thisMIn)){
                stack.remove(size-1);
                size--;
                map.put(thisMIn,map.get(before_min));
            }else{
                stack.remove(size-1);
                size--;
            }
        }
    }

    public int top() {
        return stack.get(size-1);
    }

    public int getMin() {
        return stack.get(map.get(thisMIn));
    }
}
