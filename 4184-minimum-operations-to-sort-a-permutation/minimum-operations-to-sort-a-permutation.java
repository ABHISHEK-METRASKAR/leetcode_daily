import java.util.function.IntPredicate;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;
        
        // Find the index of element 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }
        
        // Helper function to check if the elements form an increasing sequence with a given step
        final int finalZero = zero;
        IntPredicate check = step -> {
            for (int i = 1; i < n; i++) {
                int prev = (finalZero + (i - 1) * step + n) % n;
                int curr = (finalZero + i * step + n) % n;
                if (nums[prev] > nums[curr]) {
                    return false;
                }
            }
            return true;
        };
        
        int ans = Integer.MAX_VALUE;
        
        // Case 1: Increasing to the right (step = 1)
        if (check.test(1)) {
            ans = Math.min(ans, zero);              // Rotate directly
            ans = Math.min(ans, n - zero + 2);      // Reverse, Rotate, Reverse
        }
        
        // Case 2: Increasing to the left (step = -1)
        if (check.test(-1)) {
            ans = Math.min(ans, zero + 2);          // Rotate, Reverse
            ans = Math.min(ans, n - zero);          // Reverse, Rotate
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}