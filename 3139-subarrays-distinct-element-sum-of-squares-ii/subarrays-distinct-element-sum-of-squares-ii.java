
import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;

    long[] sum, squareSum, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        squareSum = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastSeen = new HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = lastSeen.getOrDefault(nums[i], -1);

            update(1, 0, n - 1, prev + 1, i);

            ans = (ans + squareSum[1]) % MOD;
            lastSeen.put(nums[i], i);
        }

        return (int) ans;
    }

    private void update(int node, int start, int end,
                        int left, int right) {
        if (left > end || right < start) {
            return;
        }

        if (left <= start && end <= right) {
            apply(node, start, end, 1);
            return;
        }

        pushDown(node, start, end);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, left, right);
        update(node * 2 + 1, mid + 1, end, left, right);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        squareSum[node] =
            (squareSum[node * 2] + squareSum[node * 2 + 1]) % MOD;
    }

    private void apply(int node, int start, int end, long delta) {
        long len = end - start + 1L;
        long oldSum = sum[node];

        squareSum[node] = (
            squareSum[node]
            + 2L * delta % MOD * oldSum % MOD
            + len * delta % MOD * delta % MOD
        ) % MOD;

        sum[node] = (oldSum + len * delta) % MOD;
        lazy[node] = (lazy[node] + delta) % MOD;
    }

    private void pushDown(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;
        long delta = lazy[node];

        apply(node * 2, start, mid, delta);
        apply(node * 2 + 1, mid + 1, end, delta);

        lazy[node] = 0;
    }
}
