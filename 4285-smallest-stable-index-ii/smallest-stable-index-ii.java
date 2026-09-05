class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return -1;

        // Step 1: Precompute suffix minimums
        int[] suffMin = new int[n];
        suffMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }

        // Step 2: Iterate left-to-right tracking prefix max
        int prefMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            prefMax = Math.max(prefMax, nums[i]);

            // Step 3: Check instability score condition
            if (prefMax - suffMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}