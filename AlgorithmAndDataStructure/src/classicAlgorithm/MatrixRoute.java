package classicAlgorithm;

public class MatrixRoute {
    /*矩阵轨迹问题*/
    public static void main(String[] args) {

    }

    //核心方法，宏观调度
    //旋转打印正方形矩阵轨迹(90°旋转方法)
    public static void rotatePrintMatrix(int[][] matrix){
        int a=0;                //a行
        int b=0;                //b列
        int c=matrix.length-1;  //总行数索引
        int d=matrix[0].length-1;//总列数索引
        while(a<c){
            rotateEdge(matrix,a++,b++,c--,d--);
        }
    }

    public static void rotateEdge(int[][] matrix,int a,int b,int c,int d){
        int temp =0;
        for (int i=0;i<d-b;i++){
            temp=matrix[a][b+i];
            matrix[a][b+i]=matrix[c-i][b];
            matrix[c-i][b]=matrix[c][d-i];
            matrix[c][d-i]=matrix[a+i][d];
            matrix[a+i][d]=temp;
        }
    }


}
