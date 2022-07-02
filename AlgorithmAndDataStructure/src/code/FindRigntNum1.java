package code;
public class FindRigntNum1 {
    public static void main(String args[]){
        int a = 4000;
        int result;
        result = a&(~a+1);
        String finalres = Integer.toBinaryString(result);
        System.out.print(finalres);
    }
}
/*这边的思想是先取反，得到一个与原本数字相反的二进制串，为了找到原数二进制串中最右边的1
，需要对取反的数＋1，使其进位，假设原数最右边的1排在第三位，那边它右边的2个位上必定都为0，取反后都为1，
原本为1的数字变为零，，此时加上1后例：11001100，取反后为00110011，加1后为 00110100，再与
原数做&运算，得到结果0000100*/
