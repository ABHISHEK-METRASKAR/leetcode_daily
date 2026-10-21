class Solution {
    public int minOperations(String s) {
        int n = s.length();

        boolean sorted = true;
        char mn = s.charAt(0);
        char mx = s.charAt(0);

        // Single pass to check if sorted and find min/max characters
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (c < s.charAt(i - 1)) {
                sorted = false;
            }
            mn = (char) Math.min(mn, c);
            mx = (char) Math.max(mx, c);
        }

        // Case 1: Already sorted
        if (sorted) return 0;
        
        // Case 2: Length 2 and unsorted cannot be fixed with proper substrings
        if (n == 2) return -1;

        // Case 3: Minimum is at the start OR maximum is at the end -> 1 operation
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) {
            return 1;
        }

        // Case 4: If min or max is somewhere in the interior -> 2 operations
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == mn || s.charAt(i) == mx) {
                return 2;
            }
        }

        // Case 5: Otherwise -> 3 operations
        return 3;
    }
}