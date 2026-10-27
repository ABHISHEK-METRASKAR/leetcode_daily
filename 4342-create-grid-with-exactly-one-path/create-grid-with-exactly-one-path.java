class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        
        // Initialize the entire grid with obstacle cells '#'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '#';
            }
        }
        
        // Fill the first row with free cells '.'
        for (int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }
        
        // Fill the last column with free cells '.'
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        
        // Convert the 2D character array into an array of Strings
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}