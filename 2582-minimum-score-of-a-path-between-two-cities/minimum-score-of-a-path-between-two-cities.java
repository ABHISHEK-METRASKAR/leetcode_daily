class Solution {

    static class Pair {
        int city;
        int dist;

        Pair(int city, int dist) {
            this.city = city;
            this.dist = dist;
        }
    }

    int ans = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {

        List<List<Pair>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];

            graph.get(u).add(new Pair(v, d));
            graph.get(v).add(new Pair(u, d));
        }

        boolean[] visited = new boolean[n + 1];
        dfs(1, graph, visited);

        return ans;
    }

    private void dfs(int node, List<List<Pair>> graph, boolean[] visited) {

        visited[node] = true;

        for (Pair nei : graph.get(node)) {

            ans = Math.min(ans, nei.dist);

            if (!visited[nei.city]) {
                dfs(nei.city, graph, visited);
            }
        }
    }
}