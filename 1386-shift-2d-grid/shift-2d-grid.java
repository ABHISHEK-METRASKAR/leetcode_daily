class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        k %= total;

        int[][] shifted = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Current position as 1D index
                int currentIndex = i * n + j;

                // New position after k shifts
                int newIndex = (currentIndex + k) % total;

                // Convert back to 2D coordinates
                int newRow = newIndex / n;
                int newCol = newIndex % n;

                shifted[newRow][newCol] = grid[i][j];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(shifted[i][j]);
            }
            ans.add(row);
        }

        return ans;
    }
}