package code;
/*递归思想解决数组求最大值问题*/
public class FindMaxNumInArr {
    public int getMax(int [] arr){
        return process(arr,0, arr.length-1);
    }

    public int process(int[] arr,int left,int right){
        if (left ==right){
            return arr[left];
        }
        int mid = left+((right-left)>>1);
        int lefmax = process(arr,left,mid);
        int rigmax = process(arr,mid+1,right);
        return Math.max(lefmax,rigmax);

    }

    public static void main(String[] args){
        FindMaxNumInArr test = new FindMaxNumInArr();
        int [] arr = {1,2,3,4,9,15,6,8,7};
        System.out.print(test.getMax(arr));
    }
}


