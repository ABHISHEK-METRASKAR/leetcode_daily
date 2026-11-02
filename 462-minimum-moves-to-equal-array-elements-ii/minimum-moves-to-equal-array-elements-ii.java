class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int midIndex = n / 2;
       int midValue  = nums[midIndex];
       
       
        int diff = 0;
        for(int i=0; i<nums.length; i++){
            diff += Math.abs(midValue-nums[i]);
        }

        return diff;
    }
}