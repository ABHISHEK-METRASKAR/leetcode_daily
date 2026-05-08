class Solution {

    static final int MAX = 1000000;
    static int[] spf = new int[MAX + 1];

    static {
        for (int i = 0; i <= MAX; i++) {
            spf[i] = i;
        }

        for (int i = 2; i * i <= MAX; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= MAX; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
    }

    public int minJumps(int[] nums) {

        int n = nums.length;

        if (n == 1) return 0;

        // prime -> indices divisible by prime
        List<Integer>[] divisibles = new ArrayList[MAX + 1];

        for (int i = 0; i < n; i++) {

            int x = nums[i];

            while (x > 1) {

                int p = spf[x];

                if (divisibles[p] == null) {
                    divisibles[p] = new ArrayList<>();
                }

                divisibles[p].add(i);

                while (x % p == 0) {
                    x /= p;
                }
            }
        }

        boolean[] visited = new boolean[n];

        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.offer(0);
        visited[0] = true;

        int jumps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int idx = q.poll();

                if (idx == n - 1) {
                    return jumps;
                }

                // left
                if (idx - 1 >= 0 && !visited[idx - 1]) {
                    visited[idx - 1] = true;
                    q.offer(idx - 1);
                }

                // right
                if (idx + 1 < n && !visited[idx + 1]) {
                    visited[idx + 1] = true;
                    q.offer(idx + 1);
                }

                int val = nums[idx];

                // teleport only if current value itself is prime
                if (val >= 2 && spf[val] == val) {

                    List<Integer> list = divisibles[val];

                    if (list != null) {

                        for (int next : list) {

                            if (!visited[next]) {
                                visited[next] = true;
                                q.offer(next);
                            }
                        }

                        // CRITICAL OPTIMIZATION
                        divisibles[val] = null;
                    }
                }
            }

            jumps++;
        }

        return -1;
    }
}