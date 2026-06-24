class Solution {
    static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        if (n == 1) return m;
        if (m == 1) return 0;

        int size = 2 * m;

        // Initial vector for length = 2
        long[] vec = new long[size];
        for (int i = 0; i < m; i++) {
            vec[i] = i;           // up
            vec[m + i] = m - i - 1; // down
        }

        if (n == 2) {
            long ans = 0;
            for (long x : vec) ans = (ans + x) % MOD;
            return (int) ans;
        }

        // Transition matrix
        long[][] mat = new long[size][size];

        // newUp[i] = sum(down[j]) where j < i
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                mat[i][m + j] = 1;
            }
        }

        // newDown[i] = sum(up[j]) where j > i
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                mat[m + i][j] = 1;
            }
        }

        long[][] power = matrixPower(mat, n - 2);

        long[] result = multiply(power, vec);

        long ans = 0;
        for (long x : result) ans = (ans + x) % MOD;

        return (int) ans;
    }

    private long[][] matrixPower(long[][] mat, int exp) {
        int sz = mat.length;
        long[][] res = new long[sz][sz];

        for (int i = 0; i < sz; i++) res[i][i] = 1;

        while (exp > 0) {
            if ((exp & 1) == 1)
                res = multiply(res, mat);
            mat = multiply(mat, mat);
            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int sz = a.length;
        long[][] c = new long[sz][sz];

        for (int i = 0; i < sz; i++) {
            for (int k = 0; k < sz; k++) {
                if (a[i][k] == 0) continue;
                long val = a[i][k];
                for (int j = 0; j < sz; j++) {
                    if (b[k][j] == 0) continue;
                    c[i][j] = (c[i][j] + val * b[k][j]) % MOD;
                }
            }
        }

        return c;
    }

    private long[] multiply(long[][] mat, long[] vec) {
        int sz = mat.length;
        long[] res = new long[sz];

        for (int i = 0; i < sz; i++) {
            long sum = 0;
            for (int j = 0; j < sz; j++) {
                if (mat[i][j] == 0) continue;
                sum = (sum + mat[i][j] * vec[j]) % MOD;
            }
            res[i] = sum;
        }

        return res;
    }
}