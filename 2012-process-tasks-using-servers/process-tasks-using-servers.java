import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        int currentTime = 0;

        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            while (!busy.isEmpty() && busy.peek()[0] <= currentTime) {
                int[] s = busy.poll();
                free.offer(new int[]{s[1], s[2]});
            }

            if (free.isEmpty()) {
                currentTime = busy.peek()[0];
                while (!busy.isEmpty() && busy.peek()[0] <= currentTime) {
                    int[] s = busy.poll();
                    free.offer(new int[]{s[1], s[2]});
                }
            }

            int[] server = free.poll();
            int weight = server[0];
            int index = server[1];
            ans[j] = index;
            busy.offer(new int[]{currentTime + tasks[j], weight, index});
        }

        return ans;
    }
}
