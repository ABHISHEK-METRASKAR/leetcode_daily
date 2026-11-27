class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;

        int max = nums[0];
        int currentMax =nums[0];

        int min = nums[0];
        int currentMin = nums[0];

        int total = 0;

        for(int i=1; i<nums.length; i++){

            currentMax = Math.max(nums[i],currentMax+nums[i]);
            max = Math.max(max,currentMax);

            currentMin = Math.min(nums[i],currentMin+nums[i]); 
            min = Math.min(min,currentMin);

            total += nums[i];
        }

        total +=nums[0];
        if(max < 0){
            return max;
        }
        return max = Math.max(max,total-min);
    }
}