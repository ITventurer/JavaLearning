package graph;

import java.util.*;

//最小生成树
public class MinimunSpanningTree {
    public static class UnionFind{
        //key 某节点，value,往上的节点
        private HashMap<Node,Node> fathermap;
        //key某一个集合的代表节点，value ,key 所在集合的节点个数
        private HashMap<Node,Integer> sizemap;

        public UnionFind(){
            fathermap =new HashMap<Node,Node>();
            sizemap =new HashMap<Node,Integer>();

        }
        //对每一个元素建立并查集
        public void makeSets(Collection<Node> nodes){
            fathermap.clear();
            sizemap.clear();
            for (Node node:nodes){
                fathermap.put(node,node);
                sizemap.put(node,1);
            }
        }
        //找某个节点的父亲节点，也就是代表集合的那个节点
        private Node findFather(Node n){
            Stack<Node> path =new Stack<>();
            while(n!=fathermap.get(n)){
                path.add(n);
                n=fathermap.get(n);
            }
            while (!path.isEmpty()){
                fathermap.put(path.pop(),n);//找到夫节点后，把路径上的所有节点的夫节点都设置为找到的夫节点
            }
            return n;
        }

        //判断两个节点是否属于同一个集合
        public boolean isSameSet(Node a,Node b){
            return findFather(a)==findFather(b);
        }

        //合并两个集合
        public void union(Node a,Node b){
            if (a==null||b==null){
                return;
            }
            Node aDai =findFather(a);
            Node bDai =findFather(b);
            if (aDai!=bDai){
                int aSetSize = sizemap.get(aDai);
                int bSetSize = sizemap.get(bDai);
                if (aSetSize<=bSetSize){
                    fathermap.put(aDai,bDai);
                    sizemap.put(bDai,aSetSize+bSetSize);
                    sizemap.remove(aDai);
                }
                else {
                    fathermap.put(bDai,aDai);
                    sizemap.put(aDai,aSetSize+bSetSize);
                    sizemap.remove(bDai);
                }
            }
        }
    }
    //实现一个比较器
    public static class EdgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight-o2.weight;//从小到大排序
        }
    }

    //K算法,有向图，如果是无向图的话，会少一半的边，因为无向图可以由有向图表示
    public static Set<Edge> kruskalMST(Graph graph){
        UnionFind unionFind =new UnionFind();//并查集
        unionFind.makeSets(graph.nodes.values());//把图里面所有点击包装成各自的集合
        PriorityQueue<Edge> priorityQueue =new PriorityQueue<>(new EdgeComparator());//建队，小根堆，安排从小到大顺序排列

        for (Edge edge:graph.edges){
            priorityQueue.add(edge);//把边给加进去，进去后从小到大排列
        }

        Set<Edge> result =new HashSet<>();//结果集
        while (!priorityQueue.isEmpty()){
            Edge edge =priorityQueue.poll();
            if (!unionFind.isSameSet(edge.from,edge.to)){//判断这条边的两个点是不是属于同一个集合，不是的话把这两个点并到一个集合里面
                result.add(edge);
                unionFind.union(edge.from,edge.to);
            }
        }
        return result;
    }

    //prim算法，无向图
    public static Set<Edge> primMST(Graph graph){
        PriorityQueue<Edge> priorityQueue =new PriorityQueue<>(new EdgeComparator());//解锁的边进入小根堆
        HashSet<Node> nodeSet =new HashSet<>();//哪些点被解锁出来了
        HashSet<Edge> edgeSet =new HashSet<>();//解锁的边

        Set<Edge> result =new HashSet<>();//结果集
        for (Node node:graph.nodes.values()){//随机挑一个点解锁,防森林，如果图中有森林，则遍历时会把每个森林都考虑到
            if (!nodeSet.contains(node)){//判断解锁的是否为新点
                nodeSet.add(node);//把挑选出的点添加的解锁点集
                for (Edge edge:node.edges){
                    if (!edgeSet.contains(edge)){
                        edgeSet.add(edge);
                        priorityQueue.add(edge);//把解锁的边加入到小根堆中
                    }
                }
                while (!priorityQueue.isEmpty()){
                    Edge edge =priorityQueue.poll();//挑选一个最小的边
                    Node tonode = edge.to;//挑选一个可能的新点
                    if (!nodeSet.contains(tonode)){//判断解锁的是不是新点
                        nodeSet.add(tonode);//新点的话加到解锁点集中
                        result.add(edge);//并把这条边加到结果集中
                        for (Edge nextEdge:tonode.edges){//遍历新点的边集
                            if (!edgeSet.contains(nextEdge)){//如果有新的边解锁
                                edgeSet.add(nextEdge);//添加到边解锁集合中
                                priorityQueue.add(nextEdge);//并把解锁边添加到小根堆中
                            }
                        }
                    }
                }
            }
            //break;如果没有森马，则可以用for break随机挑选一个点，如果有森林，则必须遍历所有的点,此时break不需要写
        }
        return result;
    }

}
