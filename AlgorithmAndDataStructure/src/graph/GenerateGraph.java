package graph;
/*结构转换图*/
public class GenerateGraph {
    //给你一个矩阵,nx3矩阵,[weight,from,to],产生一个自己定义的图
    public static Graph generateGraph(int[][] matrix){
        Graph graph =new Graph();
        for(int i=0;i<matrix.length;i++){
            //matrix[0,0],matrix[0,1],matrix[0,2]
            Integer weight = matrix[i][0];
            Integer from =matrix[i][1];
            Integer to =matrix[i][2];
            if (!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }
            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }
            Node fromNode =graph.nodes.get(from);
            Node toNode =graph.nodes.get(to);

            Edge newEdge = new Edge(weight,fromNode,toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
