package algorithm.dynamicprogramming;

/**
 * @author zhaixt
 * @date 2019/11/14 21:41
 */
public class MaximumSubarray {
    /*
    * LeetCode 53. Maximum Subarray
    *给定一个数组，求这个数组的连续子数组中，最大的那一段的和。
    *如数组[-2,1,-3,4,-1,2,1,-5,4] 的子段为：
    *[-2,1]、[1,-3,4,-1]、[4,-1,2,1]、...、[-2,1,-3,4,-1,2,1,-5,4]，和最大的是[4,1,2,1]，为6。
    * */
    public static void main(String[] args) {
        int[] array = {-2,1,-3,4,-1,2,1,-5,4};
        int max = maxSubArray(array);
        System.out.println(max);
    }
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        if(len == 0)
            return 0;
        int [] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i<len;i++){
            dp[i] = (dp[i-1]+nums[i] > nums[i]) ? dp[i-1]+nums[i] : nums[i];
            if (dp[i]>max)
                max = dp[i];
        }
        return max;
    }
}
