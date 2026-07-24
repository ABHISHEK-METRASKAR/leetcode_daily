class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[] dp1 = new boolean[MAX];
        boolean[] dp2 = new boolean[MAX];
        boolean[] dp3 = new boolean[MAX];

        for (int val : nums) {
            // Form triples
            for (int x = 0; x < MAX; x++) {
                if (dp2[x]) {
                    dp3[x ^ val] = true;
                }
            }

            // Form pairs
            for (int x = 0; x < MAX; x++) {
                if (dp1[x]) {
                    dp2[x ^ val] = true;
                }
            }

            // Single element
            dp1[val] = true;
        }

        int ans = 0;
        for (int x = 0; x < MAX; x++) {
            if (dp1[x] || dp3[x]) {
                ans++;
            }
        }

        return ans;
    }
}