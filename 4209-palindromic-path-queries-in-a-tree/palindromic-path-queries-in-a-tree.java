import java.util.*;

class Solution {
    int LOG;
    int[][] up;
    int[] tin, tout, depth, parent, size;
    int[] bit;
    int[] head, to, next;
    int edgeCount;

    void addEdge(int u, int v) {
        to[edgeCount] = v;
        next[edgeCount] = head[u];
        head[u] = edgeCount++;
    }

    void update(int idx, int val) {
        for (int i = idx; i < bit.length; i += i & -i) {
            bit[i] ^= val;
        }
    }

    int query(int idx) {
        int res = 0;
        for (int i = idx; i > 0; i -= i & -i) {
            res ^= bit[i];
        }
        return res;
    }

    void rangeXor(int l, int r, int val) {
        update(l + 1, val);
        update(r + 2, val);
    }

    int rootMask(int u) {
        return query(tin[u] + 1);
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s,
                                         String[] queries) {
        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        head = new int[n];
        Arrays.fill(head, -1);
        to = new int[2 * (n - 1)];
        next = new int[2 * (n - 1)];

        for (int[] e : edges) {
            addEdge(e[0], e[1]);
            addEdge(e[1], e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        size = new int[n];
        Arrays.fill(parent, -1);

        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0, count = 0, timer = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];
            tin[u] = timer++;
            order[count++] = u;

            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];

                if (parent[v] != -1) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[parent[u]] += size[u];
        }

        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + size[u] - 1;
        }

        up = new int[LOG][n];

        for (int u = 0; u < n; u++) {
            up[0][u] = parent[u];
        }

        for (int j = 1; j < LOG; j++) {
            for (int u = 0; u < n; u++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }
        }

        bit = new int[n + 2];
        char[] chars = s.toCharArray();

        for (int u = 0; u < n; u++) {
            rangeXor(tin[u], tout[u], 1 << (chars[u] - 'a'));
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] != c) {
                    int delta = (1 << (chars[u] - 'a'))
                              ^ (1 << (c - 'a'));

                    rangeXor(tin[u], tout[u], delta);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int w = lca(u, v);

                int mask = rootMask(u) ^ rootMask(v)
                         ^ (1 << (chars[w] - 'a'));

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }
}