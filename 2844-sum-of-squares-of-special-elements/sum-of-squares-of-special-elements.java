class Solution {
    public int sumOfSquares(int[] nums) {
        int len = nums.length;
        int sum  = 0;
        for(int i=0; i<len; i++){
            int oneIndx = i+1;
            if(len % oneIndx == 0){
                sum += nums[i] * nums[i];    
            }
        }
        return sum;
    }
}