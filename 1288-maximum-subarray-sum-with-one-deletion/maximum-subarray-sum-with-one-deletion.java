class Solution {
    public int maximumSum(int[] nums) {
        int noDelete = nums[0];
        int  oneDelete= 0;
        int max = nums[0];

        for(int i=1; i<nums.length; i++){
            oneDelete = Math.max(noDelete, oneDelete+nums[i]);

            noDelete = Math.max(noDelete+nums[i],nums[i]);

            max = Math.max(max,Math.max(oneDelete,noDelete));
        }

        return max;
    }
}