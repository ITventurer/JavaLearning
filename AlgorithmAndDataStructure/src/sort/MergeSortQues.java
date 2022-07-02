package sort;
//问题求解小数组小和问题，小和，即数组中一个数左边小于他的数之和，数组小和即数组中每个数的总小和。
//利用归并排序求解代码如下。
public class MergeSortQues {
    public static void main(String [] args){
        int [] arr = {1,3,4,2,5};
    System.out.println(getMinSum(arr));
    }
    public static int getMinSum(int[] arr){
        if (arr==null||arr.length==1){
            return 0;
        }
        return process(arr,0, arr.length-1);
    }
    public static int process(int[] arr,int l,int r){
        if (l==r){
            return 0;//数组长度为空或者为1时小和不存在；
        }
        int mid = l+((r-l)>>1);
        return process(arr,l,mid)
                +process(arr,mid+1,r)+
                merge(arr,l,mid,r);
    }
    public static int merge(int [] arr,int l,int mid,int r){
        int p1=l;
        int p2=mid+1;
        int[] help= new int[r-l+1];
        int i=0;
        int result=0;
        while(p1<=mid&&p2<=r){
            result+=(arr[p1]<arr[p2]?arr[p1]*(r-p2+1) :0);
            help[i++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];//=号的问题
        }
        while(p1<=mid){
            help[i++] = arr[p1++];//每刷一个数，i+1即help指针往后移动一位。
        }
        while(p2<=r){
            help[i++] =arr[p2++];
        }
        for (i=0;i< help.length;i++){
            arr[i+l] = help[i];
        }
        return result;
    }
}
