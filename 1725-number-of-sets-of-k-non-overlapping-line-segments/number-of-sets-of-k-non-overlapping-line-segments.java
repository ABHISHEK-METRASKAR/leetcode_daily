class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int K = 2 * k;

        if (K > N) {
            return 0;
        }

        // Calculate C(N, K) % MOD = N! / (K! * (N - K)!) % MOD
        long numerator = 1;
        long denominator = 1;

        for (int i = 1; i <= K; i++) {
            numerator = (numerator * (N - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }

        // Modular multiplicative inverse using Fermat's Little Theorem
        return (int) ((numerator * modInverse(denominator, MOD)) % MOD);
    }

    private long modInverse(long base, int exp) {
        return power(base, exp - 2);
    }

    private long power(long base, long exp) {
        long res = 1;
        base = base % MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
 }