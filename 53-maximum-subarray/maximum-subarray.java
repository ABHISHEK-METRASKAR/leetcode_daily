class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int currentMax = 0;

        for(int i=0; i<nums.length; i++){
            currentMax = Math.max(nums[i], currentMax+nums[i]);

            max = Math.max(max, currentMax);
        }
        return max;
    }
}