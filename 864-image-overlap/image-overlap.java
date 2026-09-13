class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();

        // Step 1: Collect coordinates of all 1s in both images
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    list1.add(new int[]{i, j});
                }
                if (img2[i][j] == 1) {
                    list2.add(new int[]{i, j});
                }
            }
        }

        // Map to count the frequency of each offset vector (r1 - r2, c1 - c2)
        Map<Integer, Integer> offsetCount = new HashMap<>();
        int maxOverlap = 0;

        // Step 2: Calculate displacement offset for every pair of 1s
        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                int rowOffset = p1[0] - p2[0];
                int colOffset = p1[1] - p2[1];

                // Encode (rowOffset, colOffset) pair into a unique integer key
                // Shift rowOffset by 100 to ensure positive values (since -N < offset < N)
                int key = (rowOffset + 100) * 1000 + (colOffset + 100);

                int count = offsetCount.getOrDefault(key, 0) + 1;
                offsetCount.put(key, count);

                maxOverlap = Math.max(maxOverlap, count);
            }
        }

        return maxOverlap;
    }
}