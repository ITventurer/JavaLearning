package tree;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
//二叉树集合
//二叉树相关算法
public class BinaryTree {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int val){
            value=val;
        }
    }

    //先序打印所有节点
    //先序，头节点，左子树，右子树
    public static void  pre(Node head){
        if (head==null){
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    //中序打印，先左，后头，再右边
    public static void in(Node head){
        if (head==null){
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    //后续遍历，先左右，再右，再头
    public static void pos(Node head){
        if (head==null){
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }
    //非递归前序遍历
    public static void preNoRecur(Node head){
        System.out.println("pre-order");
        if (head!=null){
            Stack<Node> stack =new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()){
                head=stack.pop();
                System.out.println(head.value+" ");
                if (head.right!=null){
                    stack.push(head.right);
                }
                if (head.left!=null){
                    stack.push(head.left);
                }
            }
        }
    }
    //非递归后序
    public static void postNoRecur(Node head){
        System.out.println("post-order");
        if (head!=null){
            Stack<Node> stack =new Stack<>();
            Stack<Node> help =new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()){
                head=stack.pop();
                help.push(head);
                if (head.left!=null){
                    stack.push(head.left);
                }
                if (head.right!=null){
                    stack.push(head.right);
                }
            }
            while (!help.isEmpty()){
                System.out.print(help.pop().value+" ");
            }
        }
    }
    //中序遍历
    public static void inNoRecur(Node head){
        System.out.println("in-order");
        if (head!=null){
            Stack<Node> stack =new Stack<>();
            while(head!=null||!stack.isEmpty()){
                if (head!=null){
                    stack.push(head);
                    head=head.left;//一直压左子树入栈
                }
                else {
                    head=stack.pop();
                    System.out.print(head.value+" ");
                    head= head.right;
                }
            }
        }
    }

    //非递归后续遍历，中一个栈
    public static void posNoRecurOneStack(Node h){
        System.out.println("后续遍历，只用一个栈");
        if(h!=null){
            Stack<Node> stack =new Stack<>();
            stack.push(h);
            Node c=null;            //核心思想h追踪已经打印过的节点
            while (!stack.isEmpty()){
                c=stack.peek();
                if (c.left!=null&&h!=c.left&&h!=c.right){//左数没处理就去处理左树
                    stack.push(c.left);
                }else if (c.right!=null&&h!=c.right){//右树没处理就去处理右树
                    stack.push(c.right);
                }else {//都处理完了，自己没处理就处理自己，处理完回到没处理过的地方继续走逻辑分支
                    System.out.print(stack.pop().value+" ");
                    h=c;
                }
            }
        }
        System.out.println();
    }

    //按层遍历，用队列实现
    public static void level(Node head){
        if (head==null){
            return;
        }
        Queue<Node> queue =new LinkedList<>();//队列引用是抽象类，对立实现是linkedlist
        queue.add(head);
        while ((!queue.isEmpty())){
            Node cur =queue.poll();
            System.out.println(cur.value);
            if (cur.left!=null){
                queue.add(cur.left);
            }
            if (cur.right!=null){
                queue.add(cur.right);
            }
        }
    }

    //统计二叉树的最大宽度，即一层嘴哥节点个数
    public static int getMaxWidth(Node head){
        if (head==null){
            return 0;
        }
        Queue<Node> queue =new LinkedList<>();
        HashMap<Node,Integer> levelmap =new HashMap<>();
        levelmap.put(head,1);//头节点目前所在层数为1
        queue.add(head);
        int curlevel=1;//统计当前在哪一层
        int curlevelnodes=0;//当前层宽度是多少
        int max=0;//最大宽度
        while (!queue.isEmpty()){
            Node cur= queue.poll();//弹出操作
            int curnodelevel = levelmap.get(cur);//查看当前层数是多少
            if (cur.left!=null){
                levelmap.put(cur.left,curnodelevel+1);//加节点，赛层数
                queue.add(cur.left);
            }
            if (cur.right!=null){
                levelmap.put(cur.right,curnodelevel+1);
                queue.add(cur.right);
            }
            if (curnodelevel==curlevel){
                curlevelnodes++;
            }
            else {//更新最大值，层数＋1，节点是从1开始
                max =Math.max(max,curlevelnodes);//保存层数值最大值
                curlevel++;//处理下一层
                curlevelnodes=1;//下一层节点数从1开始增加;
            }
        }
        max =Math.max(max,curlevelnodes);//最后一层处理的结果,因为上述代码是新的一层到来时候再处理上一层的，所以最后一层单独处理
        return max;
    }
    /*总体思路，每一个节点进队列时候，把他的层数保存到map里面，并统计个数，在出队列的时候，看他是不是同一层的，如果
    * 层数出现变化，那么这一层就结束了，把这一层的节点数和之前保存的最大值最对比，谁大取谁，然后进入下一层处理，操作一样。进入新的一层
    * 时候，层数＋1，层节点数从头开始计数。*/

    //不用哈希表
    public static int getMaxWidthWithoutMap(Node head){
        if (head==null){
            return 0;
        }
        Queue<Node> queue =new LinkedList<>();
        queue.add(head);
        Node curend =head;//当前层最后节点
        Node nextend=null;//下一层最后节点
        int max=-0;
        int curlevelnodes =0;//当前层节点数
        while (!queue.isEmpty()){
            Node cur =queue.poll();
            if (cur.left!=null){
                queue.add(cur.left);
                nextend=cur.left;//每加一个节点，最后节点指针就跟随到这个节点
            }
            if (cur.right!=null){
                queue.add(cur.right);
                nextend=cur.right;
            }
            curlevelnodes++;//记录当前层数节点数
            if (cur==curend){//一旦发现当前指针指到了当层最后一个节点
                max=Math.max(max,curlevelnodes);//保存上一层节点数到全局max里面
                curlevelnodes=0;//每层节点数重置
                curend=nextend;//层节点数移指到下一层最后一个
            }
        }
        return max;
    }

    //序列化，将内存中的树结构转变为线性结构保存起来，反序列化指上述相反过程
    //前序序列化
    public static Queue<String> preSerial(Node head){
        Queue<String> ans =new LinkedList<>();
        pres(head,ans);
        return ans;
    }

    public static void pres(Node head,Queue<String> ans){
        if (head==null){
            ans.add(null);
        }
        else {
            ans.add(String.valueOf(head.value));//递归处理
            pres(head.left,ans);
            pres(head.right,ans);
        }
    }

    //反序列话（先序）
    public static Node buildByPreQueue(Queue<String> prelist){
        if (prelist==null||prelist.size()==0){
            return null;
        }
        return preb(prelist);
    }

    public static Node preb(Queue<String> prelist){
        String value =prelist.poll();
        if (value==null){
            return null;
        }
        Node head =new Node(Integer.valueOf(value));
        head.left=preb(prelist);
        head.right=preb(prelist);
        return head;
    }

    //按层序列化
    public static Queue<String> levelSerial(Node head){
        Queue<String> ans =new LinkedList<>();
        if (head==null){
            ans.add(null);
        }
        else {
            ans.add(String.valueOf(head.value));
            Queue<Node> queue =new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()){
                head =queue.poll();
                if (head.left!=null){
                    ans.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                }
                else {
                    ans.add(null);
                }
                if (head.right!=null){
                    ans.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                }
                else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    //反序列化，按层
    public static Node buildTreeByLevelQueue(Queue<String> levellist){
        if (levellist==null||levellist.size()==0){
            return null;
        }
        Node head =generateNode(levellist.poll());
        Queue<Node> queue =new LinkedList<Node>();
        if (head!=null){
            queue.add(head);
        }
        Node node =null;
        while (!queue.isEmpty()){
            node =queue.poll();
            node.left = generateNode(levellist.poll());
            node.left =generateNode(levellist.poll());
            if (node.left!=null){
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right);
            }
        }
        return head;
    }
    //产生一个新节点
    public static Node generateNode(String value){
        if (value==null){
            return null;
        }
        return new Node(Integer.parseInt(value));
    }

    //打印一棵二叉树
    public static void printTree(Node head){
        System.out.println("打印一颗二叉树");
        printOrder(head,0,"H",17);
        System.out.println();
    }

    public static void printOrder(Node head,int height,String to,int len){
        if (head==null){
            return;
        }
        printOrder(head.right,height+1,"v",len);
        String val = to+head.value+to;
        int lenM = val.length();
        int lenL = (len-lenM)/2;
        int lenR = len-lenM-lenL;
        val = getSpace(lenL)+val+getSpace(lenR);
        System.out.println(getSpace(height*len)+val);
        printOrder(head.left,height+1,"^",len);
    }

    //空格函数
    public static String getSpace(int len){
        String space=" ";
        StringBuffer buf = new StringBuffer("");
        for (int i=0;i<len;i++){
            buf.append(space);
        }
        return buf.toString();
    }

    //一棵二叉树，找到给定节点的后继节点
    //定义给定结构的二叉树
    public static class NodeS{
        int val;
        NodeS left;
        NodeS right;
        NodeS parent;
        public NodeS(int value){
            val =value;
        }
    }

    //找后继节点，此节点的后续节点，或者他是谁的后继节点(中序遍历)
    public static NodeS getSuccessorNode(NodeS node){
        if (node ==null){
            return node;
        }
        if (node.right!=null){
            return  getLeftMost(node.right);//找右子树的最左节点
        }
        else {//没有右孩子
            NodeS parent = node.parent;
            while (parent!=null&&parent.left!=node){//只要父节点存在，且当前不是父亲节点的左孩子
                node =parent;
                parent=node.parent;
            }
            return parent;
        }
    }

    public static NodeS getLeftMost(NodeS node){
        if (node ==null){
            return node;
        }
        while (node.left!=null){
            node=node.left;
        }
        return node;
    }

    //对折纸，打印凹凸痕问题
    public static void printShape(int N){//N为对折几次
        printProcess(1,N,true);//1从哪一层开始，true代表一开始为凹面
    }

    public static void printProcess(int level,int N,boolean down){
        if(level>N){
            return;
        }
        printProcess(level+1,N,down);
        System.out.println(down?"凹":"凸");
        printProcess(level+1,N,false);
    }


    //给定一个头节点，判断这棵树是否是平衡树
    public static class Info{
        boolean isBananced;
        int height;
        Info(boolean b,int h){
            isBananced=b;
            height=h;
        }
    }
    public static boolean isbalanced(Node head){
        return process(head).isBananced;
    }
    public static Info process(Node head){//树递归
        if (head==null){
            return  new Info(true,0);
        }
        Info leftinfo  = process(head.left);
        Info rightinfo = process(head.right);
        int height = Math.max(leftinfo.height,rightinfo.height)+1;
        boolean isbalanced = false;
        if (leftinfo.isBananced==true&&rightinfo.isBananced==true&&Math.abs(leftinfo.height- rightinfo.height)<=1){
            isbalanced=true;
        }

        return new Info(isbalanced,height);
    }

    public static class Employees{
        int happy;
        List<Employees> nexts;
    }

    public static class InfoData{
        int yes;
        int no;
        public InfoData(int come,int nocome){
            this.yes = come;
            this.no = nocome;
        }
    }

    public static InfoData processInfo(Employees e){
        if (e.nexts.isEmpty()){
            return new InfoData(e.happy,0);
        }
        int yes = e.happy;
        int no =0;
        for (Employees nexts:e.nexts){
            yes+=processInfo(nexts).no;//员工不来所产生的快乐值
            no+=Math.max(processInfo(nexts).yes,processInfo(nexts).no);//本人不来所产生的快乐值最大值
        }
        return new InfoData(yes,no);
    }

}
