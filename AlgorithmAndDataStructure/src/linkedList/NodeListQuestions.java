package linkedList;

import java.util.HashMap;
import java.util.Stack;

//链表类题目
public class NodeListQuestions {
    public static class Node{
        public int value;
        public Node next;
        public Node(int n){
            value =n;
        }

    }
    //链表长度为奇数时，返回中间节点，链表长度为偶数时返回上中点
    public static Node midOrUpMid(Node head){
        if (head==null||head.next==null||head.next.next==null){
            return head;
        }
        //能来到这一步说明至少有3个节点
        Node slow = head.next;//慢指针，一次走一个节点
        Node fast = head.next.next;//快指针，一次走2个节点
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    //判断一个单链表是不是回文结构,空间复杂度度o(n)，只适合笔试用
    public static boolean isPalinDrome(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while(cur!=null){//入栈
            stack.add(cur);
            cur =cur.next;
        }
        while(head!=null){
            if (head.value!=stack.pop().value){
                return false;
            }
            head=head.next;
        }
        return true;
    }
    //额外空间复杂度o(1)的方法
    public static boolean isPalinDrom2(Node head){
        if (head==null||head.next==null){
            return true;
        }
        Node slow = head;
        Node fast =head;
        while(fast.next!=null&&fast.next.next!=null){//寻找链表的中点或上中点
            slow=slow.next;
            fast=fast.next.next;
        }
        Node n1=slow;//n1指向中点
        Node n2 =n1.next;//指向中点后一个
        n1.next=null;//中点指向空
        Node temp=null;//中间暂存节点
        while(n2.next!=null){
            temp =n2.next;
            n2.next=n1;
            n1 =n2;
            n2=temp;
        }
        Node n3 = n1;//末端头节点
        n2=head;//左端头节点
        boolean res =true;
        while(n1!=null&&n2!=null){
            if (n1.value!=n2.value){
                res =false;
                return res;
            }
            n1=n1.next;
            n2=n2.next;
        }
        n1=n3.next;//保存倒数第二个节点;
        n3.next=null;//最后一个指空
        while(n1!=null){//把链表返回回来
            n2 =n1.next;
            n1.next =n3;
            n3 =n1;
            n1 =n2;
        }
        return res;
    }
    //给你某个值，请将链表按值大小，小于给定值的放左边，等于的放中间，大于的放右边
    public static Node listPartition(Node head, int pivot){//pivot给定值
        if (head==null){
            return null;
        }
        Node cur =head;
        int count =0;
        while (cur!=null){//检查有多少个节点
            cur =cur.next;
            count++;
        }
        Node[] arr = new Node[count];
        cur =head;
        count=0;
        for (count=0;count<arr.length;count++){//将节点塞进数组
            arr[count] = cur;
            cur=cur.next;
        }
        arrpartition(arr,pivot);//数组分片操作
        for (int i=1;i<arr.length;i++){//串起来链表
            arr[i-1].next=arr[i];
        }
        arr[arr.length-1].next=null;//最后一个节点指空
        return arr[0];//返回头节点
    }

    public static void arrpartition(Node[] arr,int pivot){
        int min =-1;
        int max=arr.length;
        int index=0;
        while(index<max){
            if (arr[index].value==pivot){
                index++;
            }
            else if(arr[index].value<pivot){
                swap(arr,index++,++min);
            }
            else {
                swap(arr,index,--max);
            }
        }
    }

    public static void swap(Node[] arr,int l,int r){
        Node temp =arr[r];
        arr[r] =arr[l];
        arr[l] =temp;
    }

    //分成大、中、小区域，解决上述问题
    public static Node listpartition2(Node head,int pivot){
        Node sH = null;//小于区域头指针
        Node sT = null;//小于区域尾指针
        Node eH = null;//等于区域头指针
        Node eT = null;//等于区域尾指针
        Node lH = null;//大于区域头指针
        Node lT = null;//大于区域尾指针
        Node next = head;//保存下一个节点
        while (head!=null){
            next =head.next;//保存下一个节点信息
            head.next=null;//现在节点指针指空
            if (head.value<pivot){
                if (sH==null){
                    sH=head;
                    sT=head;
                }
                else {
                    sT.next=head;
                    sT=head;
                }
            }
            else if (head.value==pivot){
                if (eH==null){
                    eH =head;
                    eT=head;
                }else {
                    eT.next = head;
                    eT=head;
                }
            }
            else {
                if (lH==null){
                    lH=head;
                    lT=head;
                }
                else {
                    lT.next=head;
                    lT=head;
                }
            }
            head=next;
        }
        //小于区域的尾巴连等于区域的头，等于区域的尾巴连大于区域的头，最后返回头节点
        if (sT!=null){//小于区域是否存在
            sT.next=eH;//存在小于区域指向等于区域的头
            eT=eT==null?sT:eT;//判断谁去连大于区域的头
        }
        if (eT!=null){//等于区域是否存在
            eT.next=lH;
        }//若都不存在，则返回大于区域的头
        return sH!=null?sH:(eH!=null?eH:lH);
    }
    //
    public static class NodeRandom{
        public int value;
        public NodeRandom next;
        public NodeRandom rand;
        public NodeRandom(int val){
            value =val;
        }
    }
    //复制一个单链表，此链表有一个next指针和一个random指针，用哈希表来实现
    public static NodeRandom copylistWithRand(NodeRandom head){
        HashMap<NodeRandom,NodeRandom> map = new HashMap<>();
        NodeRandom cur =head;
        while (cur!=null){//全部塞进哈希表里面
            map.put(cur,new NodeRandom(cur.value));//值是一个节点类型，但是没有设置next和rand指针
            cur=cur.next;
        }
        cur =head;

        while (cur!=null){
            map.get(cur).next=map.get(cur.next);//新节点的下一个就是老节点下一个的键对应的值
            map.get(cur).rand=map.get(cur.rand);//同上
            cur=cur.next;
        }
        return map.get(head);
    }

    //复制链表，不用哈希表实现
    public static NodeRandom copyListWithRand(NodeRandom head){
        if (head==null){
            return null;
        }
        //第一步，复制每一个节点并串到原节点后面
        NodeRandom cur =head;
        NodeRandom next=null;
        while(cur!=null){
            next =cur.next;//保存指针下一个节点值
            cur.next =new NodeRandom(cur.value);//把指针的next指向新生成的节点
            cur.next.next=next;//新节点next指向原先next指向的节点；
            cur=next;//来到原先的第二个节点；
        }

        //串rand指针
        cur =head;
        NodeRandom copycur = null;
        while (cur!=null){
            next =cur.next.next;//一对一对处理，保存老节点的下一个指针
            copycur=cur.next;
            copycur.rand=cur.rand!=null?cur.rand.next:null;//老节点后面跟的是新节点，复制的节点的指针应该指向新节点
            cur=next;
        }
        //断开新老节点之间的指针
        cur=head;
        NodeRandom copyhead =cur.next;
        //从新节点第一个开始，隔一个依次链接新节点；
        while(cur!=null){//考虑一个原数组的复原问题
            next =cur.next.next;
            copycur =cur.next;
            cur.next=next;
            copycur.next=next!=null?next.next:null;//看后面有没有新节点了
            cur=next;
        }
        return copyhead;
    }
    //给你两个单链表的头节点，可能有换也可能无环，请写一个函数判断两个链表是否相交，如果相交，返回交点，若无返回null，t:O(n),s:O(1)
    //先判断链表是否有环，返回入环的第一个节点
    //主函数
    public static Node getIntersectNode(Node head1,Node head2){
        if (head1==null||head2==null){
            return null;
        }
        Node loop1=getLoopNode(head1);
        Node loop2=getLoopNode(head2);
        if (loop1==null&&loop2==null){
            return noLoopList(head1,head2);
        }
        if (loop1!=null&&loop2!=null){
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;
    }

    public static Node getLoopNode(Node head){
        if (head==null||head.next==null||head.next.next==null){
            return null;//有换起码三个节点才可能
        }
        Node slow =head.next;//慢指针
        Node fast =head.next.next;//快指针
        while(slow!=fast){
            if (fast.next==null||fast.next.next==null){
                return null;//判断是否是没换的
            }
            slow=slow.next;
            fast=fast.next.next;
        }
        //到这一步肯定是环了
        fast=head;
        while(fast!=slow){
            slow=slow.next;
            fast=fast.next;
        }
        return fast;
    }
    //两个无环链表怎么找交点
    public static Node noLoopList(Node headA,Node headB){
        if (headA==null||headA.next==null||headB==null||headB.next==null){
            return null;
        }
        Node cur1=headA;
        Node cur2=headB;
        int n=0;
        while(cur1.next!=null){//cur1和cur2来到各自链表的尾节点
            cur1=cur1.next;
            n++;
        }
        while (cur2.next!=null){
            cur2=cur2.next;
            n--;
        }
        if (cur1!=cur2){//单链表，如果有交点，那么必定返回共同一个终点，因为相交以后共享指针，必定指到null为止
            return null;//终点地址不一样，肯定不相交
        }
        cur1 =n>0?headA:headB;//谁长指谁
        cur2 =cur1==headA?headB:headA;//指短的头
        n=Math.abs(n);//算绝对长短
        while(n!=0){
            cur1=cur1.next;
            n--;
        }
        while(cur1!=cur2){
            cur1=cur1.next;
            cur2=cur2.next;
        }
        return cur1;
    }
    //一条有环一条无环，不可能相交的，因为只要相交，那么必定共享一个终点，必定会形成环
    //两个有环的相交
    public static Node bothLoop(Node head1,Node loop1,Node head2,Node loop2){
        Node cur1 =null;
        Node cur2 =null;
        if (loop1==loop2){
            cur1=head1;
            cur2=head2;
            int n=0;
            while(cur1!=loop1){
                n++;
                cur1=cur1.next;
            }
            while (cur2!=loop1){
                n--;
                cur2=cur2.next;
            }
            cur1=n>0?head1:head2;
            cur2=cur1==head1?head2:head1;
            n=Math.abs(n);
            while(n!=0){
                cur1=cur1.next;
                n--;
            }
            while(cur1!=cur2){
                cur1=cur1.next;
                cur2=cur2.next;
            }
            return cur1;
        }
        else {
            cur1=loop1.next;
            while(cur1!=loop1){
                if (cur1==loop2){
                    return loop1;
                }
                cur1=cur1.next;
            }
        return null;
        }
    }




}
