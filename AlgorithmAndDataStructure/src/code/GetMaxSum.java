package code;
//求连续字串最大和
class GetMaxSum {
    public  static  void main(String[] args){
        int[] arr = {1,-1,2,-2,3,5,6};
        System.out.print(maxSubArray(arr));
    }

    public static int maxSubArray(int[] nums) {
        int sum = nums[0];
        int maxsum=nums[0];
        if(nums.length==1){
            maxsum = sum;
        }
        else{
            int index =0;
            for(int i=index;i<nums.length;i++,index++){
                sum += nums[i];
                if(sum>nums[0]){
                    maxsum = sum;
                }

            }
        }
        return maxsum-nums[0];
    }
}