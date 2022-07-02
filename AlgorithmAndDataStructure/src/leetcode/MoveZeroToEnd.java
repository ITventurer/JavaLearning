package leetcode;
/*
* 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

请注意 ，必须在不复制数组的情况下原地对数组进行操作。*/
public class MoveZeroToEnd {
    public static void main(String[] args) {

    }

    public static void moveZeroes(int[] nums) {
        int[] help = new int[nums.length];
        int normalIndex = 0;
        int zeroIndex= nums.length;
        for (int i =0;i< nums.length;i++){
            if (nums[i]!=0){
                help[normalIndex++]=nums[i];
            }
            else{
                help[--zeroIndex]=nums[i];
            }
        }

        for (int i =0;i< nums.length;i++){
            nums[i]= help[i];
        }
    }
}
