package leetcode;
//给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。
public class NumOfExcel {
    public static void main(String[] args) {
        System.out.println(26*('Z'-64));
    }
    public static int titleToNumber(String columnTitle) {
        int res = 0;
        char[] chars = columnTitle.toCharArray();
        int length = chars.length;
        for (int i=0;i<length;i++,length--){
            res+=Math.pow(26,length-1)*(chars[i]-64);
        }
        return res;
    }
}
