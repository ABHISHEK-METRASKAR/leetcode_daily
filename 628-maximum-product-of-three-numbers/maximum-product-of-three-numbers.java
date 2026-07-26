class Solution {
    public int maximumProduct(int[] nums) {
        
        int max = Integer.MIN_VALUE;
        int secMax = Integer.MIN_VALUE;
        int thrMax = Integer.MIN_VALUE;

        int min = Integer.MAX_VALUE;
        int secMin = Integer.MAX_VALUE;

        for(int i=0; i<nums.length;i++){

            if(nums[i]>max){
                thrMax = secMax;
                secMax = max;
                max = nums[i];          
            }
            else if(nums[i]>secMax){
                thrMax = secMax;
                secMax = nums[i];

            }
            else if(nums[i]>thrMax){
                thrMax = nums[i];
            }

            if(nums[i]<min){
                secMin = min;
                min = nums[i];
            }
            else if(nums[i]<secMin){
                secMin = nums[i];
            }
        }

        return Math.max(max*secMax*thrMax,max*min*secMin);
    }
}