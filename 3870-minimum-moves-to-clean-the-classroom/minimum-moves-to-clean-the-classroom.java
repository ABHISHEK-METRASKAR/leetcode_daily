import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        // litterIndex[r][c] = bit number of litter at this cell
        int[][] litterIndex = new int[m][n];

        int startR = 0;
        int startC = 0;
        int litterCount = 0;

        // Find start and assign each litter a bit
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } 
                else if (ch == 'L') {
                    litterIndex[r][c] = litterCount;
                    litterCount++;
                }
            }
        }

        // No litter to collect
        if (litterCount == 0) {
            return 0;
        }

        /*
         * mask:
         * 1 -> litter still needs to be collected
         * 0 -> litter already collected
         *
         * Example:
         * 3 litter => 111
         * all collected => 000
         */
        int fullMask = (1 << litterCount) - 1;

        /*
         * visited[row][col][energy][mask]
         */
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litterCount];

        /*
         * State:
         * [row, col, remainingEnergy, mask]
         */
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] {
            startR,
            startC,
            energy,
            fullMask
        });

        visited[startR][startC][energy][fullMask] = true;

        int moves = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one BFS level
            while (size-- > 0) {

                int[] state = queue.poll();

                int r = state[0];
                int c = state[1];
                int currentEnergy = state[2];
                int mask = state[3];

                // All litter collected
                if (mask == 0) {
                    return moves;
                }

                // Cannot make another move
                if (currentEnergy == 0) {
                    continue;
                }

                // Try 4 directions
                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid or obstacle
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n ||
                        classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    char cell = classroom[nr].charAt(nc);

                    int nextEnergy;
                    int nextMask = mask;

                    // Reset area
                    if (cell == 'R') {
                        nextEnergy = energy;
                    } else {
                        nextEnergy = currentEnergy - 1;
                    }

                    // Collect litter
                    if (cell == 'L') {
                        int bit = litterIndex[nr][nc];

                        // Remove this litter from mask
                        nextMask &= ~(1 << bit);
                    }

                    // Avoid duplicate states
                    if (!visited[nr][nc][nextEnergy][nextMask]) {

                        visited[nr][nc][nextEnergy][nextMask] = true;

                        queue.offer(new int[] {
                            nr,
                            nc,
                            nextEnergy,
                            nextMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}