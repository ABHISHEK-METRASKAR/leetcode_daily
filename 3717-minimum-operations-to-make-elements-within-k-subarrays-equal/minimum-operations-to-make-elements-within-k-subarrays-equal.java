class Solution {

    class Fenwick {
        int n;
        int[] count;
        long[] sum;

        Fenwick(int n) {
            this.n = n;
            count = new int[n + 1];
            sum = new long[n + 1];
        }

        void update(int i, int c, long s) {
            while (i <= n) {
                count[i] += c;
                sum[i] += s;
                i += i & -i;
            }
        }

        int queryCount(int i) {
            int ans = 0;

            while (i > 0) {
                ans += count[i];
                i -= i & -i;
            }

            return ans;
        }

        long querySum(int i) {
            long ans = 0;

            while (i > 0) {
                ans += sum[i];
                i -= i & -i;
            }

            return ans;
        }

        // Find the index of the kth smallest element
        int kth(int k) {
            int idx = 0;

            for (int bit = Integer.highestOneBit(n);
                 bit > 0; bit >>= 1) {

                int next = idx + bit;

                if (next <= n && count[next] < k) {
                    idx = next;
                    k -= count[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;

        for (int v : sorted) {
            if (m == 0 || sorted[m - 1] != v) {
                sorted[m++] = v;
            }
        }

        int[] a = Arrays.copyOf(sorted, m);

        Fenwick bit = new Fenwick(m);

        // Calculate cost for every window of size x
        long[] cost = new long[n - x + 1];

        long total = 0;

        for (int i = 0; i < x; i++) {
            int idx = Arrays.binarySearch(a, nums[i]) + 1;
            bit.update(idx, 1, nums[i]);
            total += nums[i];
        }

        for (int i = 0; i <= n - x; i++) {

            // Find median
            int medianIdx = bit.kth((x + 1) / 2);
            long median = a[medianIdx - 1];

            // Calculate sum and count of elements <= median
            int leftCount = bit.queryCount(medianIdx);
            long leftSum = bit.querySum(medianIdx);

            int rightCount = x - leftCount;
            long rightSum = total - leftSum;

            // Minimum operations to make window equal
            cost[i] =
                median * leftCount - leftSum +
                rightSum - median * rightCount;

            // Slide window
            if (i < n - x) {

                int removeIdx =
                    Arrays.binarySearch(a, nums[i]) + 1;

                int addIdx =
                    Arrays.binarySearch(a, nums[i + x]) + 1;

                bit.update(removeIdx, -1, -((long) nums[i]));
                bit.update(addIdx, 1, nums[i + x]);

                total += (long) nums[i + x] - nums[i];
            }
        }

        // Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        Arrays.fill(prev, 0);

        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Option 1: Skip current element
                curr[i] = curr[i - 1];

                // Option 2: Select a window ending at i-1
                if (i >= x && prev[i - x] != INF) {

                    long candidate =
                        prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], candidate);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}