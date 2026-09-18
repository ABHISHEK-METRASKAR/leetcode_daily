class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = n + 1;

        // left[i] = minimum length of a valid subarray
        // ending at or before index i
        int[] left = new int[n];

        for (int i = 0; i < n; i++) {
            left[i] = INF;
        }

        int ans = INF;

        int sum = 0;
        int start = 0;
        int best = INF;

        for (int end = 0; end < n; end++) {

            sum += arr[end];

            // Shrink window if sum becomes greater than target
            while (sum > target) {
                sum -= arr[start++];
            }

            // Found a subarray with sum = target
            if (sum == target) {

                int len = end - start + 1;

                // Store minimum valid subarray seen so far
                best = Math.min(best, len);

                // Check if another non-overlapping
                // subarray exists before 'start'
                if (start > 0 && left[start - 1] != INF) {
                    ans = Math.min(ans, len + left[start - 1]);
                }
            }

            // Carry forward the best subarray
            // found up to this index
            if (end > 0) {
                left[end] = Math.min(left[end - 1], best);
            } else {
                left[end] = best;
            }
        }

        return ans == INF ? -1 : ans;
    }
}