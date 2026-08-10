class Solution {
    public int equalPairs(int[][] grid) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        int n = grid.length;

        for(int i=0; i<n; i++){
            List<Integer> row = new ArrayList<>();
            for(int j=0; j<n; j++){
                
                row.add(grid[i][j]);

                map.put(row,map.getOrDefault(row,0)+1);
            }
        }

        int count = 0;

        for(int i=0; i<n; i++){
            List<Integer> col = new ArrayList<>();
            for(int j=0; j<n; j++){
                
                col.add(grid[j][i]);
            }
            count = count + map.getOrDefault(col,0);
        }

        return count;
    }
}