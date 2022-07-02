package sort;
//桶排序 O(N) O(N) 稳定
public class BucketSort {
    //计数排序 only for (0-200)包含0，也就是201个数
    public void countSort(int [] arr){
        if (arr==null||arr.length<2){
            return;
        }
        int max =Integer.MIN_VALUE;
        for (int i=0;i<arr.length;i++){
            max = Math.max(arr[i],max);
        }
        int[] bucket = new int[max+1];//0-200，包含边界
        for (int i=0;i<arr.length;i++){
            bucket[arr[i]]++;//如果arr[i]=200，那么就在桶200的位置上计一次
        }
        int i=0;
        for (int j =0;j<bucket.length;j++){
            while (bucket[j]-->0){//执行倒的操作
                arr[i++]=j;
            }
        }

    }
    //基数排序,非负10进制整数。
    public static void radixSort(int [] arr){
        if (arr==null||arr.length<2){
            return;
        }
        radixSort(arr,0,arr.length-1,maxbits(arr));
    }
    public static int maxbits(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i=0;i<arr.length;i++){
            max = Math.max(arr[i],max);
        }
        int counts=0;
        while(max!=0){
            counts++;
            max = max/10;
        }
        return counts;
    }

    //重载radix函数，入参表不同，digit表示最大值是几位数
    public static void radixSort(int[] arr,int L,int R,int digit){
        final int radix = 10;
        int i = 0;
        int j = 0;
        int [] help =new int[R-L+1];
        for (int d=1;d<=digit;d++){//假设最大数是三位，那么个位，十位和百位分别都要进出桶一次，也就是三次，有多少位就进出多少次
            int [] count = new int[radix];//0-9的数字数组
            for (i=L;i<=R;i++){//遍历数组
                j = getDigit(arr[i],d);//这个函数入参d表示目前是按哪位数塞桶的，d=1就按照各位，返回值为数组中的各位数字
                count[j]++;//0-9数字中，每出现一个就计一次数+1;
            }
            for (i=1;i<radix;i++){//对count数组做前几个和处理
                count[i] = count[i]+count[i-1];//从第2位开始，第二等于此位值加上前面的和，意思为小于i的值的个数共有多少个
            }
            for (i=R;i>=L;i--){//从数组后面开始遍历
                j = getDigit(arr[i],d);//判断数组中数的个此轮位上数字，例如第一轮就是各位数字是什么。
                help[count[j]-1] = arr[i];//入桶操作，例如209，各位数字位=为9，count[9]表示个位数字小于等于9的一共有6位，对饮下标位0-5
                count[j]--;                 //→又因为是倒着遍历的，那么现在的数一定是放在0-5间的最后一位，也就是5，所以要减少1，每放回一个数
            }                               //count此处的值都要减少一个，以保证下一个以9为个位数字的放回下标正确
            for (i=L,j=0;i<=R;i++,j++){
                arr[i] = help[j];//将help数组值刷回arr中，再进行下一轮判断十位数上判断
            }
        }
    }
    //返回对应位置上的数字
    public static int getDigit(int num,int digit){
        int res;
        int temp = (int)(num/(Math.pow(10,digit-1)));
        res = temp%10;
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2,10,27,5,16,65,289};
        radixSort(arr);
        for (int nums:arr){
            System.out.print(nums+" ");
        }
    }
}
