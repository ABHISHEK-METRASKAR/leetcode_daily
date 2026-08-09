class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length;

        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for(int i=1; i<n; i++){
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }

        int[] suffixSum = new int[n];
        suffixSum[n-1] = nums[n-1];
        for(int i=n-2; i>=0; i--){
            suffixSum[i] = suffixSum[i+1] + nums[i];
        }

        for(int i=0; i<n; i++){
            int leftSum = prefixSum[i] - nums[i];
            int rightSum = suffixSum[i] - nums[i];
            if(leftSum == rightSum){
                return i;
            }
        }
        return -1;
    }
}