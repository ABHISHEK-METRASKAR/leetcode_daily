class Solution {
    private static final long MOD1 = 1000000007L;
    private static final long MOD2 = 1000000009L;
    private static final long BASE = 31L;

    private long[] pow1, pow2, hash1, hash2;
    private long[] sortedHash1, sortedHash2;

    private long getHash(long[] hash, long[] pow, int l, int r, long mod) {
        return (hash[r] - hash[l] * pow[r - l] % mod + mod) % mod;
    }

    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);

        pow1 = new long[n + 1];
        pow2 = new long[n + 1];
        hash1 = new long[n + 1];
        hash2 = new long[n + 1];
        sortedHash1 = new long[n + 1];
        sortedHash2 = new long[n + 1];

        pow1[0] = pow2[0] = 1;

        for (int i = 0; i < n; i++) {
            pow1[i + 1] = pow1[i] * BASE % MOD1;
            pow2[i + 1] = pow2[i] * BASE % MOD2;

            hash1[i + 1] = (hash1[i] * BASE + nums[i] + 1L) % MOD1;
            hash2[i + 1] = (hash2[i] * BASE + nums[i] + 1L) % MOD2;

            sortedHash1[i + 1] =
                (sortedHash1[i] * BASE + sorted[i] + 1L) % MOD1;
            sortedHash2[i + 1] =
                (sortedHash2[i] * BASE + sorted[i] + 1L) % MOD2;
        }

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            for (int i = 0; i < n && valid; i += k) {
                long target1 = getHash(sortedHash1, pow1, i, i + k, MOD1);
                long target2 = getHash(sortedHash2, pow2, i, i + k, MOD2);

                boolean found = false;

                for (int s = 0; s < k; s++) {
                    long suffix1 = getHash(hash1, pow1, i + s, i + k, MOD1);
                    long prefix1 = getHash(hash1, pow1, i, i + s, MOD1);

                    long suffix2 = getHash(hash2, pow2, i + s, i + k, MOD2);
                    long prefix2 = getHash(hash2, pow2, i, i + s, MOD2);

                    long rotation1 =
                        (suffix1 * pow1[s] % MOD1 + prefix1) % MOD1;
                    long rotation2 =
                        (suffix2 * pow2[s] % MOD2 + prefix2) % MOD2;

                    if (rotation1 == target1 && rotation2 == target2) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    valid = false;
                }
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}