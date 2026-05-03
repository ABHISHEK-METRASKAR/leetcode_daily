class Solution {
    public int jump(int[] nums) {
       int jumps = 0;
       int currentEnd = 0;
       int fasthest = 0;

       for(int i=0; i<nums.length-1; i++){
            fasthest = Math.max(fasthest,i+nums[i]);

            if(i==currentEnd){
                jumps++;
                currentEnd = fasthest;
            }
       } 
       return jumps;
    }
}