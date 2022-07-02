package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//返回升序排列后的链表
public class AscendingLinkedList {
    public static void main(String[] args) {

    }

    public static ListNode sortList(ListNode head) {
        if (head==null){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        list.add(cur.val);
        while(cur.next!=null){
            cur=cur.next;
            list.add(cur.val);
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        cur = head;
        int index = 0;
        while(index<list.size()){
            head.val=list.get(index);
            head=head.next;
            index++;
        }
        return cur;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
