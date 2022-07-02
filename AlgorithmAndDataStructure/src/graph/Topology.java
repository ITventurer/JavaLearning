package graph;

import java.util.*;

public class Topology {
    //拓扑排序算法
    public List<Node> topologySort(Graph graph){
        if (graph==null){
            return null;
        }
        HashMap<Node,Integer> inmap = new HashMap<>();//key Node,value 这个点所剩余入度
        Queue<Node> zeroInNode = new LinkedList<>();//入度为0的节点
        //首先遍历一遍点集，把入度为0的点添加到0入度队列中
        for (Node next:graph.nodes.values()){
            if (next.in==0){
                zeroInNode.add(next);
            }
        }
        List<Node> result = new ArrayList<>();//结果集

        while (!zeroInNode.isEmpty()){
            Node cur =zeroInNode.poll();
            result.add(cur);
            for (Node next:cur.nexts){
                inmap.put(next,inmap.get(next)-1);//把节点直属的节点入度值减去1;
                if (next.in==0){
                    zeroInNode.add(next);
                }
            }
        }
        return result;
    }
}
