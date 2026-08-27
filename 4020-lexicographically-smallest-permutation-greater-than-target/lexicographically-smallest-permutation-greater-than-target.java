class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] globalFreq = new int[26];
        for (char c : s.toCharArray()) {
            globalFreq[c - 'a']++;
        }

        // Iterate backwards from the maximum matching prefix down to length 0
        for (int i = n - 1; i >= 0; i--) {
            int[] currentFreq = globalFreq.clone();
            boolean prefixValid = true;

            // Step 1: Check if target[0 ... i-1] can be formed using s
            for (int k = 0; k < i; k++) {
                int idx = target.charAt(k) - 'a';
                if (currentFreq[idx] <= 0) {
                    prefixValid = false;
                    break;
                }
                currentFreq[idx]--;
            }

            if (!prefixValid) continue;

            // Step 2: Try to pick the smallest available character at index i strictly greater than target[i]
            int targetCharIdx = target.charAt(i) - 'a';
            for (int c = targetCharIdx + 1; c < 26; c++) {
                if (currentFreq[c] > 0) {
                    // Step 3: Construct the lexicographically smallest result
                    StringBuilder sb = new StringBuilder();
                    sb.append(target, 0, i);        // Matching prefix
                    sb.append((char) ('a' + c));    // Strictly greater char at i
                    currentFreq[c]--;

                    // Fill remaining suffix in ascending order
                    for (int ch = 0; ch < 26; ch++) {
                        while (currentFreq[ch] > 0) {
                            sb.append((char) ('a' + ch));
                            currentFreq[ch]--;
                        }
                    }

                    return sb.toString();
                }
            }
        }

        return "";
    }
}