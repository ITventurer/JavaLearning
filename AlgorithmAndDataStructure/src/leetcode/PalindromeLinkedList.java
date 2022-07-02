package leetcode;

import code.Test;
import linkedList.SingleList;

import java.util.Stack;

//是否是回文链表
public class PalindromeLinkedList {
    public static void main(String[] args) {

    }

    public  boolean isPalinDrome(SingleList head){
        if (head==null){
            return true;
        }
        SingleList cur = head;
        Stack<Integer> stack = new Stack<>();
        while(cur!=null){
            stack.push(cur.data);
           cur= cur.next;
        }
        cur=head;
        boolean res = true;
        while(cur!=null){
            if (cur.data!=stack.pop()){
                res=false;
                break;
            }
            cur=cur.next;
        }
        return res;
    }
}
