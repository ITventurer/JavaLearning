package leetcode;

import java.util.ArrayList;
import java.util.List;

//杨辉三角
public class YangHuiTriangle {
    public static void main(String[] args) {
        printTriangle(10);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rows = new ArrayList<>();
        for (int i=0;i<numRows;i++){
            List<Integer> list = new ArrayList<>();
            for (int j=0;j<=i;j++){
                if (j==0||j==i){
                    list.add(1);
                }else
                {
                    list.add(rows.get(i-1).get(j-1)+rows.get(i-1).get(j));
                }
            }
            rows.add(list);
        }
        return rows;
    }

    //失败品
    public static  void printTriangle(int n){
        for (int i=0;i<n;i++){
            for (int j=0;j<=i;j++){
                if (j==0||j==i){
                    System.out.print(1);
                }
                else{
                    System.out.print(j-1+i-1+i-1+j+"\t");
                }
            }
            System.out.println();
        }
    }
}
