package algorithm.dynamicprogramming;

/**
 * @author zhaixt
 * @date 2019/11/14 21:41
 * 最大和子序列 -- 分治
 * https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
 */
public class 最大和序列 {
    /*
    * LeetCode 53. Maximum Subarray
    *给定一个数组，求这个数组的连续子数组中，最大的那一段的和。
    *如数组[-2,1,-3,4,-1,2,1,-5,4] 的子段为：
    *[-2,1]、[1,-3,4,-1]、[4,-1,2,1]、...、[-2,1,-3,4,-1,2,1,-5,4]，和最大的是[4,1,2,1]，为6。
    * */
    public static void main(String[] args) {
        int[] array = {-2,1,-3,4,-1,2,1,-5,4};
        // int max = maxSubArray(array);
        int max = myMaxSubArray(array);
        System.out.println(max);
    }

    public static int myMaxSubArray(int[] nums) {
        if(null == nums || nums.length < 1){
            return 0;
        }
        int maxByIndex = nums[0];
        int max = nums[0];
        for(int index=1;index<nums.length;index++){
            // 获取截止到index这个的最大值，考虑是num[index]需不需要我这个max(index-1)子串
            maxByIndex = Math.max(nums[index],maxByIndex+nums[index]);
            //找到后，只需要比较各个位置的截止最大值即可
            max = Math.max(max,maxByIndex);

        }
        return max;
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

    public static int maxSubArray3(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

}
