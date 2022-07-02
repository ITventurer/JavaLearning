package linkedList;
//单链表
public class SingleList {
    public int data;
    public SingleList next;
    public SingleList(int data, SingleList next){
        this.data = data;
        this.next = next;
    }
    public SingleList reverseList(SingleList head){
        SingleList pre =null;
        SingleList next =null;
        while(head!=null){
            next =head.next;//先把头节点指向的下一个保存到next里面；
            head.next = pre;//再把头节点指空；
            pre=head;//把head节点的值保存到pre节点里面;
            head=next;//把之前头节点指向的值保存到
        }
        return pre;
    /*反转单链表总结，首先创建一个pre保存上一个节点对象，next保存当前节点(head)指向的下一个节点(head.next)
    * ，后将当前节点(head)的指针head.next指向还是那个一个对象pre(翻转指向方向),然后将当前节点(head)赋值给pre，
    * 再将后面的节点逐渐左移，即把next保存的下一个节点赋值给head，后面重复这个过程，这样就可以实现单链表的翻转*/
    }
    public SingleList delNum(SingleList head,int num){
        while(head!=null){
            if (head.data!=num){
                break;
            }
            head =head.next;//首先头节点不为空，遍历一遍链表，如果头节点及其后连续的几个节点的值都等于要删除的节点的值
            //则直接将头节点移动到第一个不需要删除的节点，之前的节点没有引用会被jvm自动释放掉。
        }
        SingleList pre =head;
        SingleList cur =head;
        while(cur!=null){
            if (cur.data==num){
                pre.next = cur.next;
            }
            else {
                pre =cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
/*删除单链表中某个出现多次的值时，首先验证要被删除的值是不是连续出现在链表头部，如果是的话，直接将head节点移动到第一个不需要删除
* 的节点处，声明2个新的节点pre和cur，将head节点赋值给pre和cur,然后开始遍历链表，知道cur节点为null时停止遍历，再遍历时
* ，如果遇到需要删除的节点，那么直接将上一个节点的指向pre.next指向现在节点的指向cur.next，如果不是，就将现在节点的信息cur保存到
* 上一个信息节点pre里面,在大循环里面将cur.next节点移动至当前节点cur处理,知道cur=cur.next=null时跳出循环。*/
