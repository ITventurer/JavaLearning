package graph;

public class Edge {
    public int weight;//权重
    public Node from;//起点
    public Node to;//终点
    public Edge(int weight,Node from ,Node to){
        this.weight =weight;
        this.from =from;
        this.to=to;
    }
}
