class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int incCurrMax = 1;
        int incMax = 1;

        for(int i=1; i<nums.length; i++){
            if(nums[i] > nums[i-1]){
                incCurrMax++;
            }
            else{
                incCurrMax = 1;
            }
            incMax = Math.max(incCurrMax, incMax);
        }

        int desCurrMax = 1;
        int desMax = 1;

        for(int i=1; i<nums.length; i++){
            if(nums[i] < nums[i-1]){
                desCurrMax++;
            }
            else{
                desCurrMax = 1;
            }
            desMax = Math.max(desCurrMax, desMax);
        }

        return Math.max(desMax,incMax);
    }
}