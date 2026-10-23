class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1; // -1 has all bits set to 1 in 32-bit two's complement representation
        
        for (int i = 0; i < nums.length; i++) {
            // If an element is not in its correct sorted position, accumulate its bitwise AND
            if (nums[i] != i) {
                ans &= nums[i];
            }
        }
        
        // If the array was already sorted, ans remains -1, so return 0. Otherwise, return max(ans, 0).
        return Math.max(ans, 0);
    }
}