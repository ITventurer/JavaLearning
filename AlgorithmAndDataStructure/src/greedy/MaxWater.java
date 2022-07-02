package greedy;

public class MaxWater {
    public static void main(String[] args) {
        int[] arr = {10,1,1,10};
        System.out.println(maxWater(arr));
    }
    //一个整型数组，索引i，height[i]为高度,为这个数组两个点所能构成的最大容积是多少
    //解法,贪心,左右双指针,谁小谁移动，知道越界,用max抓到最大值
    public static int maxWater(int[] height ){
        int left=0;
        int right = height.length-1;
        int res=0;
        int lowLength=-1;
        int shorter = -1;
        while(left<right){
            lowLength = right-left;
            if (height[left]<=height[right]){
                shorter = height[left];
                left++;
            }
            else {
                shorter = height[right];
                right--;
            }
            res = Math.max(res,shorter*lowLength);
        }
        return res;
    }
}
