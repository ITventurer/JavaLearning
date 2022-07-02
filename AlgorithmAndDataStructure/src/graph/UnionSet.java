package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

//并查集
public class UnionSet {
    public static class Node<V>{
        V value;
        public Node(V v){
            value=v;
        }
    }
    public static class UnionSetVersion<V>{
        public HashMap<V,Node<V>> nodes;//将数据包装成node
        public HashMap<Node<V>,Node<V>> parents;//每个阶段对应的父节点
        public HashMap<Node<V>,Integer> sizeMap;//头节点和其大小(有多少指向它的节点)

        public UnionSetVersion(List<V> values){
            for (V value: values){
                Node<V> node = new Node<>(value);
                nodes.put(value,node);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public  Node<V> findFather(Node<V> cur){
            Stack<Node<V>> path = new Stack<>();//容器
            while(cur!=parents.get(cur)){//一直找到cur的父节点
                path.add(cur);//把路径上的全加到容器里
                cur=parents.get(cur);
            }
            while(!path.isEmpty()){
                parents.put(path.pop(),cur);//把路径上的所有节点的父节点都设置为找到的cur节点
            }
            return cur;
        }

        public boolean isSameSet(V v1,V v2){
            if (!nodes.containsKey(v1)||!nodes.containsKey(v2)){
                return false;
            }
            return findFather(nodes.get(v1))==findFather(nodes.get(v2));
        }

        public void Union(V v1,V v2){
            if (!nodes.containsKey(v1)||!nodes.containsKey(v2)){
                return;
            }
            Node<V> v1head = findFather(nodes.get(v1));
            Node<V> v2head = findFather(nodes.get(v2));
            if (v1head!=v2head){
                int v1SetSize =sizeMap.get(v1head);
                int v2SetSize =sizeMap.get(v2head);
                if (v1SetSize<=v2SetSize){
                    parents.put(v1head,v2head);
                    sizeMap.put(v2head,v1SetSize+v2SetSize);
                    sizeMap.remove(v1head);
                }else {
                    parents.put(v2head,v1head);
                    sizeMap.put(v1head,v1SetSize+v2SetSize);
                    sizeMap.remove(v2head);
                }

            }

        }
        //一个用户有三个属性，若两个用户有一个对应词条相同，则两个用户为同一人，一共有多少用户
        public static class User{
            public String a;
            public String b;
            public String c;
            public User(String name,String id,String clas){
                a=name;
                b=id;
                c=clas;
            }
        }

        public static int getUserNums(User[] user){
            return 10;
        }


    }
}
