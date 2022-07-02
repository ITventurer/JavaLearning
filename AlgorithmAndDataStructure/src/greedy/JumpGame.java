package greedy;

public class JumpGame {
    public static void main(String[] args) {
        int[] arr = {2,3,1,1,4};
        int[] arr1 = {0};
        System.out.println(jumpGame(arr));
    }
    /*给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个下标。
    nums = [2,3,1,1,4] true
    * */
    //动态维护一个最远到达距离
    //三步走：第一步判断当前为自豪是都可到达
    //如果可以，则到达并维护最远到达距离
    //如果最远到达距离超过最后的位置，那么返回true，反之返回fasle
    public static boolean jumpGame(int[] nums){
        int rightMost = 0;
        for (int i=0;i<nums.length;i++){
            if (i<=rightMost){
                rightMost = Math.max(rightMost,i+nums[i]);
                if (rightMost>=nums.length-1){
                    return true;
                }
            }
        }
        return false;
    }

}
