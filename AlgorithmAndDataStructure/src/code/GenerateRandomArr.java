package code;
/*随机测试数组产生器*/
//for test
public class GenerateRandomArr {
    public static int[] generateRandomArr(int maxValxue,int maxSize){
        int [] arr= new int [(int)(Math.random()*maxSize+1)];//随机产生一个长度为1-maxSize长度的数组
        for (int i =0;i< arr.length;i++){
            arr[i] = (int)((maxValxue+1)*Math.random())-(int)(maxValxue*Math.random());//保证可能产生负数
        }
        return arr;
    }
}
