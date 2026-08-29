class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        
        // Sort indices based on their corresponding values in nums
        Arrays.sort(indices, (a, b) -> Integer.compare(nums[a], nums[b]));
        
        int[] res = new int[n];
        List<Integer> groupIndices = new ArrayList<>();
        List<Integer> groupValues = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[indices[i]] - nums[indices[i - 1]] > limit) {
                // Sort indices so smaller original positions get smaller values
                Collections.sort(groupIndices);
                for (int j = 0; j < groupIndices.size(); j++) {
                    res[groupIndices.get(j)] = groupValues.get(j);
                }
                groupIndices.clear();
                groupValues.clear();
            }
            groupIndices.add(indices[i]);
            groupValues.add(nums[indices[i]]);
        }
        
        // Process the final group
        if (!groupIndices.isEmpty()) {
            Collections.sort(groupIndices);
            for (int j = 0; j < groupIndices.size(); j++) {
                res[groupIndices.get(j)] = groupValues.get(j);
            }
        }
        
        return res;
    }
}