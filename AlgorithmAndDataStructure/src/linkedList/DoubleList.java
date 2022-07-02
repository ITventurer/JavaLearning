package linkedList;
//双向链表
public class DoubleList {
    int data;
    DoubleList next;
    DoubleList last;
    public DoubleList(int data,DoubleList next,DoubleList pre){
        this.data = data;
        this.next = next;
        this.last = pre;
    }
    public DoubleList reverseDoubleList(DoubleList head){
        DoubleList pre=null;
        DoubleList next = null;
        while(head!=null){
            next = head.next;//把head指向的下一个节点保存到next里面；
            head.next =pre;//修改head，next指向前一个
            head.last =next;//将head,last(原本指向上一个的)向后指；
            pre =head;//将目前的head保存到上一个节点信息里面，左移
            head =next;//将下一个节点赋值给head节点，重复上述操作直到完成所有翻转

        }
        return head;
    }
}
