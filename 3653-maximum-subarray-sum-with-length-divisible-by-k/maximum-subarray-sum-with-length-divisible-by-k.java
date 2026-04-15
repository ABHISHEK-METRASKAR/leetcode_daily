class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n+1];

        for(int i=0; i<nums.length; i++){
            prefix[i+1] = prefix[i]+nums[i];
        }
        HashMap<Integer,Long> map = new HashMap<>();
        long maxSum = Long.MIN_VALUE;

        for(int i=0; i<=n; i++ ){
            int mod = i%k;

            if(map.containsKey(mod)){
                long prevMin = map.get(mod);
                maxSum = Math.max(maxSum, prefix[i]-prevMin);
                map.put(mod,Math.min(prefix[i], prevMin));
            }
            else{
                map.put(mod,prefix[i]);
            }
        }
        return maxSum;
    }
}