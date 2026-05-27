class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();

        backtrack(candidates, target, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int[] candidates, int target, int start,
                           List<Integer> current,
                           List<List<Integer>> result) {

        // Base Case
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Stop if target becomes negative
        if (target < 0) {
            return;
        }

        // Try all candidates
        for (int i = start; i < candidates.length; i++) {

            // Choose
            current.add(candidates[i]);

            // Recurse (same i because reuse is allowed)
            backtrack(candidates, target - candidates[i], i, current, result);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}
