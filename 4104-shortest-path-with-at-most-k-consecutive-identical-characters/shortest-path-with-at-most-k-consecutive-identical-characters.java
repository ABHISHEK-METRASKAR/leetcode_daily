import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        long[][] dist = new long[n][k + 1];

        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[2], b[2])
        );

        dist[0][1] = 0;
        pq.offer(new long[]{0, 1, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();

            int node = (int) curr[0];
            int count = (int) curr[1];
            long cost = curr[2];

            if (cost != dist[node][count]) {
                continue;
            }

            if (node == n - 1) {
                return (int) cost;
            }

            for (int[] edge : adj[node]) {
                int next = edge[0];
                int weight = edge[1];

                int nextCount = labels.charAt(node) == labels.charAt(next)
                    ? count + 1 : 1;

                if (nextCount > k) {
                    continue;
                }

                long newCost = cost + weight;

                if (newCost < dist[next][nextCount]) {
                    dist[next][nextCount] = newCost;
                    pq.offer(new long[]{next, nextCount, newCost});
                }
            }
        }

        return -1;
    }
}