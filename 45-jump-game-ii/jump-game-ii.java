class Solution {
    public int jump(int[] nums) {

        int n = nums.length;
        int jumps = 0;
        int left = 0; 
        int right = 0;

        while(right < n-1){
            int maxIndex = 0;
            for(int i=left; i<=right; i++){
                maxIndex = Math.max(i+nums[i], maxIndex);
            }
            left = right+1;
            right = maxIndex;
            jumps++;
        }
        return jumps;
    }

}