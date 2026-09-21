class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] ans = new long[k];

        long[] dp = new long[k];

        for (int num : nums) {
            long[] next = new long[k];

            int rem = num % k;

            // Subarray containing only this element
            next[rem]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (r * rem) % k;
                    next[newRem] += dp[r];
                }
            }

            // Add all subarrays ending here
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}