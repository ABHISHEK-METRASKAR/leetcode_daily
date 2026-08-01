class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int startRow = x;
        int endRow = x+k-1;

        while(startRow<endRow){

            for(int i=y; i<=y+k-1; i++){
                int temp = grid[startRow][i];
                grid[startRow][i] = grid[endRow][i];
                grid[endRow][i] = temp;
            }
            startRow++;
            endRow--;
        }
        return grid;   
    }  
}