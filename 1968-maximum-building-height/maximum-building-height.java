import java.util.*;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {

        List<int[]> list = new ArrayList<>();

        // Building 1 must be height 0
        list.add(new int[]{1, 0});

        for (int[] r : restrictions) {
            list.add(new int[]{r[0], r[1]});
        }

        // If building n is not restricted
        boolean found = false;
        for (int[] r : restrictions) {
            if (r[0] == n) {
                found = true;
                break;
            }
        }

        if (!found) {
            list.add(new int[]{n, n - 1});
        }

        Collections.sort(list, (a, b) -> a[0] - b[0]);

        // Left -> Right
        for (int i = 1; i < list.size(); i++) {
            int dist = list.get(i)[0] - list.get(i - 1)[0];
            list.get(i)[1] = Math.min(list.get(i)[1],
                    list.get(i - 1)[1] + dist);
        }

        // Right -> Left
        for (int i = list.size() - 2; i >= 0; i--) {
            int dist = list.get(i + 1)[0] - list.get(i)[0];
            list.get(i)[1] = Math.min(list.get(i)[1],
                    list.get(i + 1)[1] + dist);
        }

        int ans = 0;

        // Calculate maximum peak between every adjacent restrictions
        for (int i = 1; i < list.size(); i++) {

            int id1 = list.get(i - 1)[0];
            int h1 = list.get(i - 1)[1];

            int id2 = list.get(i)[0];
            int h2 = list.get(i)[1];

            int dist = id2 - id1;

            int peak = (h1 + h2 + dist) / 2;

            ans = Math.max(ans, peak);
        }

        return ans;
    }
}