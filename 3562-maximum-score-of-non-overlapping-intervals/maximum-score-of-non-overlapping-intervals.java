import java.util.*;

class Solution {
    static class State {
        long sum;
        List<Integer> indices;

        State(long sum, List<Integer> indices) {
            this.sum = sum;
            this.indices = indices;
        }

        static State best(State a, State b) {
            if (a.sum != b.sum) {
                return a.sum > b.sum ? a : b;
            }
            // Tie-breaker: Lexicographically smaller indices list
            int len = Math.min(a.indices.size(), b.indices.size());
            for (int i = 0; i < len; i++) {
                int cmp = Integer.compare(a.indices.get(i), b.indices.get(i));
                if (cmp != 0) {
                    return cmp < 0 ? a : b;
                }
            }
            return a.indices.size() <= b.indices.size() ? a : b;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        
        // Store [l, r, weight, original_index]
        int[][] sorted = new int[n][4];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            sorted[i][0] = interval.get(0);
            sorted[i][1] = interval.get(1);
            sorted[i][2] = interval.get(2);
            sorted[i][3] = i;
        }

        // Sort intervals by right endpoint r
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[1], b[1]));

        // dp[i][k] stores optimal choice using a subset of first i intervals with at most k picks
        State[][] dp = new State[n + 1][5];
        
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0, new ArrayList<>());
            }
        }

        for (int i = 1; i <= n; i++) {
            int l = sorted[i - 1][0];
            int r = sorted[i - 1][1];
            int weight = sorted[i - 1][2];
            int origIdx = sorted[i - 1][3];

            // Binary search to find the last interval with end < l
            int prev = binarySearch(sorted, i - 1, l);

            for (int k = 1; k <= 4; k++) {
                // Option 1: Don't pick current interval
                State option1 = dp[i - 1][k];

                // Option 2: Pick current interval
                State prevState = dp[prev + 1][k - 1];
                List<Integer> newIndices = new ArrayList<>(prevState.indices);
                
                // Maintain sorted order of indices for canonical lexicographical comparison
                int pos = Collections.binarySearch(newIndices, origIdx);
                if (pos < 0) pos = -(pos + 1);
                newIndices.add(pos, origIdx);

                State option2 = new State(prevState.sum + weight, newIndices);

                // Take the better state
                dp[i][k] = State.best(option1, option2);
            }
        }

        State bestResult = dp[n][0];
        for (int k = 1; k <= 4; k++) {
            bestResult = State.best(bestResult, dp[n][k]);
        }

        int[] result = new int[bestResult.indices.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bestResult.indices.get(i);
        }
        return result;
    }

    private int binarySearch(int[][] sorted, int rightBound, int targetL) {
        int low = 0, high = rightBound - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sorted[mid][1] < targetL) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}