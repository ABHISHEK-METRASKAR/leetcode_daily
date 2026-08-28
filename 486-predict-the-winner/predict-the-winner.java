class Solution {
    public boolean predictTheWinner(int[] nums) {

        int n = nums.length;

        int[][] dp = new int[n][n];

        // When there is only one number,
        // current player takes that number
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        // Build DP for bigger ranges
        for (int i = n - 2; i >= 0; i--) {

            for (int j = i + 1; j < n; j++) {

                // Take left number
                int left = nums[i] - dp[i + 1][j];

                // Take right number
                int right = nums[j] - dp[i][j - 1];

                dp[i][j] = Math.max(left, right);
            }
        }

        // Player 1 wins or ties
        return dp[0][n - 1] >= 0;
    }
}