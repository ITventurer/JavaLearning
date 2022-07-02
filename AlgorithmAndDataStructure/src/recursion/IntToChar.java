package recursion;

public class IntToChar {
    /*a对应1，b对应2.....，例如111有aaa,ak,ka三种选择,给你一个字符串，总共有多少种选择*/


    public static int mainFunction(String str){
        if (str==null||str.length()==0){
            return 0;
        }
        char[] arr = str.toCharArray();
        return intToChar(arr,0);
    }
    public static int intToChar(char[] str ,int i){//给定字符串，给定一个字符数组，和数组当前的下标
        if (i== str.length){//表示当前下标已经越界，这个分支已经走到头了，肯定是一种可能
            return 1;
        }
        if (str[i]=='0'){//如果某个分支以0开头，没有对应字母，这个分支没有可能。
            return 0;
        }
        if (str[i]=='1'){//某个分支以1开头
            int res =intToChar(str,i+1);//自身肯定能转化为一种可能，你给我去递归下个分支去
            if (i+1<str.length){
                res+=intToChar(str,i+2);//因为只有1-26之间才能有能成为字母，所以只有1个数字和2个数字一组转字符这两种可能
            }
            return res;
        }

        if (str[i]=='2'){
            int res = intToChar(str,i+1);
            if (i+1<str.length&&(str[i+1]>'0' && str[i+1]<='6')){
                res+=intToChar(str,i+2);//(i和i+1作为一组，给我探索它后面有多少种可能)
            }
            return res;
        }
        //'3'~'9'开头没有可能性，直接从下一个位置开始递归
        return intToChar(str,i+1);

    }
}
