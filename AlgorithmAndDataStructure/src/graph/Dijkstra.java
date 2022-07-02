package graph;

import java.util.*;

//dj算法，最短路径路由算法,先决条件，路径不能为负数，两点没了路，默认长度为∞,本质思想就是贪心
public class Dijkstra {
    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> distance = new HashMap<>();//出发点要维护的距离表
        distance.put(from, 0);//把出发点作为第一个跳板塞进去
        HashSet<Node> selected = new HashSet<>();//已经选择过的节点
        Node minNode = getMinDistanceNode(distance, selected);//可选择的最小距离点
        while (minNode != null) {
            int distan = distance.get(minNode);//最小跳板节点距离
            for (Edge edge : minNode.edges) {//遍历最小距离节点直接联通的边集
                Node toNode = edge.to;//每个边通向的节点
                if (!distance.containsKey(toNode)) {//如果这个通向的节点在没有与起点直接联通
                    distance.put(toNode, distan + edge.weight);//现在更新距离表，起点到这个以跳板联通的点的距离为起点到跳板的距离
                }                                            //+跳板到联通点的距离，这个和可能不是起点到联通点的最小距离，但是起码走通了
                else {//如果tonode节点已经和起点联通了，那么就把原本的距离和跳板距离做对比，谁小去谁
                    distance.put(edge.to, Math.min(distance.get(toNode), distan + edge.weight));
                }
            }
            selected.add(minNode);//将选过的最小节点加黑名单
            minNode = getMinDistanceNode(distance, selected);//再从为比较过的节点选取最小节点
        }
        return distance;
    }


    //如何有效遍历哈希表，根据要求的值取出键
    //1、增强for循环遍历哈希表，取出entry键值对
    //2、调用entry.getKey方法取出键值，getvalues方法取出值,然后以最小值为基准点同步更新 键和值
    public static Node getMinDistanceNode(HashMap<Node, Integer> distance, HashSet<Node> selected) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distance.entrySet()) {
            Node node = entry.getKey();
            int val = entry.getValue();
            if (!selected.contains(node) && val < minDistance) {
                minNode = node;
                minDistance = val;
            }
        }
        //        for (Node node:nodes){
//            if (!selected.contains(node)){
//                newNode.add(node);
//            }
//        }
//        Node minNode =null;
//        int min = Integer.MAX_VALUE;
//        for(int i=0;i<newNode.size();i++){
//            min = Math.min(distance.get(newNode.get(i)),min);
//        }
//        for (int i=0;i<newNode.size();i++){
//            if (min==distance.get(newNode.get(i))){
//                minNode =newNode.get(i);
//                break;
//            }
//        }
        return minNode;
    }

    /*dijkstra算法优化*/
    //pop方法返回的信息体
    public class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int val) {
            this.node = node;
            this.distance = val;
        }
    }
    //改写堆结构
    public  class NodeHeap {
        private Node[] nodes;//实际的堆结构
        private HashMap<Node, Integer> heapIndexMap;//在堆上的索引，索引为-1代表曾经进来过
        private HashMap<Node, Integer> distanceMap;//从源节点出发到现节点的最小距离
        private int size;//堆上有多少个节点

        public NodeHeap(int size) {
            nodes = new Node[size];//初始化数组大小
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;//一开始堆大小为0；
        }

        public boolean isEmpty() {
            return size == 0 ? true : false;
        }

        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);//只要进来过的点都会在堆图里保存着
        }

        private boolean isInHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;//进来过并且索引位置不等于-1，表示目前在堆中
        }

        //判断要不要更新，如果需要就更新
        public void addOrUpdateOrIgnore(Node node, int distance) {//distance为node新发现的点到起点的距离
            if (isInHeap(node)) {//如果在堆里
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));//已经与起点联通了，这样维护下这个点与起点的最小距离
                insertHeapify(node, heapIndexMap.get(node));//维护完后再将原堆排序成小根堆
            }
            if (!isEntered(node)) {//这个点之前没有过记录，也就是新点
                nodes[size] = node;//加到队里去
                heapIndexMap.put(node, size);//记录它的索引
                distanceMap.put(node, distance);//更新下距离
                insertHeapify(node, size++);//堆了多了个新点，重新向上看，变成小根堆
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);//堆内最后一个顶第一个
            heapIndexMap.put(nodes[size - 1], -1);//把堆内最后一个数即交换过来的最小数的索引设置为-1，表示已经进来过了，目前不在对内堆内
            distanceMap.remove(nodes[size - 1]);//把最小节点的最小距离记录抬出去，表示后续不会再操作这个点了
            nodes[size - 1] = null;//情况数组最后一个，即原本的最小距离节点
            heapify(0, --size);//现有堆结构重新组织成小根堆
            return nodeRecord;
        }

        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);//尾部插入，小数向上调整
                index = (index - 1) / 2;
            }
        }

        private void swap(int l, int r) {
            heapIndexMap.put(nodes[l], r);//每个点对应的索引需要交换一下，
            heapIndexMap.put(nodes[r], 1);
            Node temp = nodes[l];
            nodes[l] = nodes[r];
            nodes[r] = temp;
        }

        //下沉操作
        private void heapify(int index, int size) {
            int left = 2 * index + 1;//左孩子
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(index, smallest);
                index = smallest;
                left = 2 * index + 1;
            }
        }

    }

    //改进后的dijkstra算法
    //从head出发，所有head到达的点生成每个节点的最小路径记录并返回
    public  HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);//生成总节点数大小的堆结构
        nodeHeap.addOrUpdateOrIgnore(head, 0);//从自身开始判断
        HashMap<Node, Integer> result = new HashMap<>();
        while(!nodeHeap.isEmpty()){
            NodeRecord nodeRecord =nodeHeap.pop();//跳板节点信息体
            Node cur =nodeRecord.node;//跳板节点
            int distance =nodeRecord.distance;//起点到跳板的最小距离
            for (Edge edge:cur.edges){//遍历跳板直连边
                nodeHeap.addOrUpdateOrIgnore(edge.to,edge.weight+distance);
                /*更新与跳板连同节点与起点的最小距离*/
            }
            result.put(cur,distance);
        }
        return result;
    }
}