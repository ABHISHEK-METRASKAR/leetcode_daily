class Solution {
    public int maxProduct(int[] nums) {
        int maxProd = nums[0];
        int currentMaxProd = nums[0];
        int currentMinProd = nums[0];

        for(int i=1; i<nums.length; i++){
            int num = nums[i];
            int tempMax = Math.max(num, Math.max(currentMaxProd*num,currentMinProd*num));
            int tempMin = Math.min(num, Math.min(currentMaxProd*num,currentMinProd*num));
           
           currentMaxProd = tempMax;
           currentMinProd = tempMin;

            maxProd = Math.max(currentMaxProd,maxProd);
        }

        return maxProd;
    }
}