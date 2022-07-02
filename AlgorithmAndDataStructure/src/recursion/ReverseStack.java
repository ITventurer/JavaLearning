package recursion;

import java.util.Stack;

public class ReverseStack {
    public static void main(String[] args) {
        Stack<Integer> stack =new Stack<>();
        stack.add(5);
        stack.add(4);
        stack.add(3);
        stack.add(2);
        stack.add(1);
        for (int val:stack){
            System.out.print(val+" ");
        }
        System.out.println();
        reverseStack(stack);
        for (int val:stack){
            System.out.print(val+" ");
        }
    }
    //如何不用额外的数据结构，逆序一个栈
    public static void reverseStack(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }else {
            int val = getStacklastOne(stack);//抓到最后一个数
            reverseStack(stack);//继续抓最后一个数
            stack.push(val);
        }
    }

    //抓到栈底数，并保留其上数据顺序不变
    public static int getStacklastOne(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else {
            int last = getStacklastOne(stack);//给我抓到栈底数，抓到直接返回，并把不是栈底的数给压回栈内
            stack.push(result);//不是栈底数，就给我压回去
            return last;//一直把栈底数给我返回到第一步
        }
    }
}
