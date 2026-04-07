class Solution {
    public boolean checkInclusion(String s1, String s2) {

        if(s2.length()<s1.length()) return false;
        int[] countS1 = new int[26];
        int[] countS2 = new int[26];

        for(char c : s1.toCharArray()){
            countS1[c-'a']++;
        }
        int k = s1.length();
        boolean is = false;
        for(int i=0 ;i<s2.length(); i++){
            char c = s2.charAt(i);
            countS2[c-'a']++;

            if(i>=k){
                countS2[s2.charAt(i-k)-'a']--;
            }

            if(Arrays.equals(countS1,countS2)){
                is=true;
            }
        }
        return is;
    }
}