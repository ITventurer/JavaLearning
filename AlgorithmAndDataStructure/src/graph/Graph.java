package graph;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    public HashMap<Integer,Node> nodes;//点集
    public HashSet<Edge> edges;//边集
    public Graph(){
        nodes =new HashMap<>();
        edges =new HashSet<>();
    }
}
