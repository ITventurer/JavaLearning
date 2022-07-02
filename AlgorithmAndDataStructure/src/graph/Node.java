package graph;

import java.util.ArrayList;

//统一图结构,点结构的描述
public class Node {
    public int value;//点编号
    public int in;//点的入度，指向点的线数量
    public int out;//点的出度，从点出去的边数量
    public ArrayList<Node> nexts;//点的直接邻居，点出去的直接连接点
    public ArrayList<Edge> edges;//边
    public Node(int val ){
        this.value =val;
        in =0;
        out =0;
        nexts =new ArrayList<>();
        edges =new ArrayList<>();
    }

}
