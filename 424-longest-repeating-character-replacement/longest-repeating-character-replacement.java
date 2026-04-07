class Solution {
    public int characterReplacement(String s, int k) {
        int[] countS = new int[26];
        int left = 0;
        int maxLen = 0;
        int maxFreq = 0;

        for(int right=0; right<s.length(); right++){
            char c = s.charAt(right);
            countS[c-'A']++;
            maxFreq = Math.max(maxFreq,countS[c-'A']);

            while((right-left+1)-maxFreq>k){
                countS[s.charAt(left)-'A']--;
                left++;
            }
            maxLen = Math.max(maxLen,right-left+1);
        }
        return maxLen;
    }
}