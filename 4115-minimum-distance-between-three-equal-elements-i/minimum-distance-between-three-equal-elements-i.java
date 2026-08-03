import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int ans = Integer.MAX_VALUE;

        for (List<Integer> list : map.values()) {
            for (int i = 0; i + 2 < list.size(); i++) {
                ans = Math.min(ans, 2 * (list.get(i + 2) - list.get(i)));
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}