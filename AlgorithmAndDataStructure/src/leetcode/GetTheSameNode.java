package leetcode;
//给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
public class GetTheSameNode {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
    public static void main(String[] args) {

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null)
            return null;
        if (headA.next==null && headB.next==null){
            if (headA==headB)
                return headA;
            else
                return null;
        }
        ListNode curA = headA;
        ListNode curB = headB;
        int n = 0;
        while(curA.next!=null){
            curA=curA.next;
            n++;
        }
        while(curB.next!=null){
            curB=curB.next;
            n--;
        }
        if (curA!=curB)
            return null;
        curA = n>0? headA:headB;//指向长的头
        curB = curA==headA?headB:headA;//指短的头
        n = Math.abs(n);
        //先移动
        while(n!=0){
            curA=curA.next;
            n--;
        }
        while(curA!=curB){
            curA=curA.next;
            curB=curB.next;
        }
        return curA;
    }


}
