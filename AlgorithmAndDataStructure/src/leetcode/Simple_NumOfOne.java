package leetcode;
//二进制中1的个数
public class Simple_NumOfOne {
    public static void main(String[] args) {

    }

    public static int hammingWeight(int n) {
        String binary = Integer.toBinaryString(n);
        char[] chars = binary.toCharArray();
        int res=0;
        for ( int i =0;i< chars.length;i++){
            if (chars[i]=='1') res++;
        }
        return res;
    }
}
