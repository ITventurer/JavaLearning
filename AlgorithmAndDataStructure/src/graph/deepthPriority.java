package graph;

import java.util.HashSet;
import java.util.Stack;

public class deepthPriority {
    //深度优先遍历
    public static void dfs(Node node){
        if (node ==null){
            return;
        }
        Stack<Node> stack =new Stack<>();//记录的其实就是深度路径
        HashSet<Node> set =new HashSet<>();//避免有环死循环
        stack.push(node);
        set.add(node);
        System.out.println(node.value);//具体业务具体分析
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next:cur.nexts){
                if (!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
