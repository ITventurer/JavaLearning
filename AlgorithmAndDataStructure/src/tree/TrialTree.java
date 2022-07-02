package tree;

import java.util.HashMap;
//前缀树
public class TrialTree {
    public static class Node1{
        public int pass;
        public int end;
        public Node1[] nexts;//表示的是路的数组
        public Node1(){
            pass =0;
            end=0;
            nexts = new Node1[26];
        }
    }
    //前缀树类
    public static class Trietree{
        private Node1 root;
        public Trietree(){
            root = new Node1();
        }

        public void insert(String word){
            if (word==null){
                return;
            }
            char[]str = word.toCharArray();//把输入的字符串转变为一个字符数组。
            Node1 node = root;//创建一个指向根节点的指针。
            node.pass++;//创建了一个节点，p值+1;
            int path=0;//存不存在路。
            for (int i=0;i<str.length;i++){
                path =str[i]-'a';//遍历输入的字符，判断属于哪条路
                if (node.nexts[path]==null){//判断这个字符的路存不存在
                        node.nexts[path] =new Node1();//新建路
                }
                node =node.nexts[path];//指向路的另一端
                node.pass++;
            }
            node.end++;
        }
        //查找这个字符串加入了几次
        public int search(String word){
            if (word==null){
                return 0;
            }
            char[] str = word.toCharArray();
            Node1 node = root;
            int path =0;
            for (int i=0;i< str.length;i++){
                path = str[i]-'a';//判断输入的字符要走那条路
                if (node.nexts[path]==null){
                    return 0;
                }
                node =node.nexts[path];//若有路，则跳到下一个节点
            }
            return node.end;
        }
        //统计以给定字符作为前缀的字符串个数
        public int prefirNum(String word){
            if (word==null){
                return 0;
            }
            char[] str = word.toCharArray();
            Node1 node =root;
            int path =0;
            for (int i=0;i<str.length;i++){
                path = str[i]-'a';//给的这个字符要先判断走那条路
                if (node.nexts[path]==null){
                    return 0;
                }
                node =node.nexts[path];
            }
            return node.pass;
        }
        //前缀树删除字符串记录
        public void delete(String word){
            if (search(word)!=0){//看看是否有这个字符串
                char[] str = word.toCharArray();
                Node1 node = root;
                int path = 0;
                for (int i=0;i<str.length;i++){
                    path = str[i]-'a';
                    if (--node.nexts[path].pass==0){//判断这条路的的pass值是不是为0
                        node.nexts[path]=null;//如果为0，将这条路指空
                        return;
                    }
                    node =node.nexts[path];
                }
                node.end--;
            }
        }
    }
    //字符种类很多时，用hash表的形式表示路，Inter表示asc2码值,node2表示下级节点
    public static class Node2{
        public int pass;
        public int end;
        public HashMap<Integer,Node2> nexts;
        public Node2(){
            pass=0;
            end=0;
            nexts =new HashMap<>();
        }
    }

    public static class HashTrieTree{
        private Node2 root;
        public HashTrieTree(){
            root = new Node2();
        }
        public void insert(String word){
            if (word==null){
                return;
            }
            char[] str = word.toCharArray();
            Node2 node =root;
            node.pass++;
            int path=0;
            for (int i=0;i<str.length;i++){
                path = str[i] -'a';
                if (!node.nexts.containsKey(path)){
                    node.nexts.put(path,new Node2());
                }
                node = node.nexts.get(path);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word){
            if (word==null){
                return;
            }
            char[] str =word.toCharArray();
            Node2 node = root;
            int path =0;
            for (int i=0;i<str.length;i++){
                path = str[i]-'a';
                if (--node.nexts.get(path).pass==0){
                    node.nexts.put(path,null);
                    return;
                }
                node =node.nexts.get(path);
            }
            node.end--;
        }

        public int  search(String word){
            if (word==null){
                return 0;
            }
            char[] str = word.toCharArray();
            Node2 node = root;
            int path=0;
            for (int i=0;i<str.length;i++){
                path =str[i]-'a';
                if (!node.nexts.containsKey(path)){
                    return 0;
                }
                node=node.nexts.get(path);
            }
            return node.end;
        }

        public int prefixStrSearch(String word){
            if (word==null){
                return 0;
            }
            char[] str = word.toCharArray();
            Node2 node =root;
            int path=0;
            for (int i=0;i<str.length;i++){
                path = str[i] -'a';
                if (node.nexts.containsKey(path)){
                    return 0;
                }
                node =node.nexts.get(path);
            }
            return node.pass;
        }



    }



}
