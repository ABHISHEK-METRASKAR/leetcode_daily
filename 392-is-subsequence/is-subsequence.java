class Solution {
    public boolean isSubsequence(String s, String t) {

        if(s.length() == 0) return true;
        if(s.length() > t.length()) return false;

        int left = 0; 
        int right = 0;

        while(right < t.length()){

            if(s.charAt(left) == t.charAt(right)){
                left++;
            }

            if(left == s.length()){
                return true;
            }
            right++;
        }
        return false;
    }
}