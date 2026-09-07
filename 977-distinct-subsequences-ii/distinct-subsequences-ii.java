class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        // last[c] stores the count added when character c last appeared
        long[] last = new long[26];
        long currentTotal = 0;

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            
            // Subsequences added by extending existing ones + 1 for single char 'c'
            long added = (currentTotal + 1 - last[idx] + MOD) % MOD;
            
            // Update total distinct subsequences
            currentTotal = (currentTotal + added) % MOD;
            
            // Update the record for this character
            last[idx] = (last[idx] + added) % MOD;
        }

        return (int) currentTotal;
    }
}