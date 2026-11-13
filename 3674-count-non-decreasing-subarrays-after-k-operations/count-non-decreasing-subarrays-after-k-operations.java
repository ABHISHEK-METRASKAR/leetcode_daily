class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
         long ans = 0;
        long cost = 0;
        
        Deque<int[]> dq = new ArrayDeque<>();
        int j = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            int count = 1;

            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                int[] top = dq.pollLast();
                int nextNum = top[0];
                int nextCount = top[1];
                count += nextCount;
                cost += (long) (num - nextNum) * nextCount;
            }
            dq.addLast(new int[]{num, count});

            while (cost > k) {
                int[] front = dq.peekFirst();
                int rightmostNum = front[0];
                int rightmostCount = front[1];
                
                cost -= (long) (rightmostNum - nums[j]);
                if (rightmostCount == 1) {
                    dq.pollFirst();
                } else {
                    front[1]--;
                }
                j--;
            }

            ans += (j - i + 1);
        }

        return ans;
    }
}