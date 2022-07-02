package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//宽度优先遍历
public class WidthPriority {
    public static void bfs(Node node){
        if (node ==null){
            return;
        }
        Queue<Node> queue =new LinkedList<>();//队列
        HashSet<Node> set = new HashSet<>();//集合,用来避免环的发生
        queue.add(node);
        set.add(node);
        while(!queue.isEmpty()){
            Node cur = queue.poll();//弹出一个打印一个
            System.out.println(cur.value);
            for (Node val:cur.nexts){
                if(!set.contains(val)){//集合里面有的就不用添加到队列里面去了，用来避免图中的环
                    queue.add(val);
                    set.add(val);
                }
            }

        }
    }
}
