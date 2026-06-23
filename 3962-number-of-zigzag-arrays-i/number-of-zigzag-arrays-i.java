class Solution {
    static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l;

        long[] dp = new long[m + 1];
        java.util.Arrays.fill(dp, 1);

        for (int step = 0; step < n - 1; step++) {
            long prefix = 0;

            for (int i = 0; i <= m; i++) {
                long cur = dp[i];
                dp[i] = prefix;
                prefix = (prefix + cur) % MOD;
            }

            // reverse dp
            for (int i = 0, j = m; i < j; i++, j--) {
                long temp = dp[i];
                dp[i] = dp[j];
                dp[j] = temp;
            }
        }

        long ans = 0;
        for (long x : dp) {
            ans = (ans + x) % MOD;
        }

        return (int) ((ans * 2) % MOD);
    }
}