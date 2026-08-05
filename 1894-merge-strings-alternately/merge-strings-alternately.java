class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder str = new StringBuilder();

        int i = 0;
        int j = 0;

        while(i<word1.length() && j<word2.length()){
            char ch1 = word1.charAt(i);
            char ch2 = word2.charAt(j);
            str.append(ch1);
            str.append(ch2);
            i++;
            j++;
        }

        while(i<word1.length()){
            char ch = word1.charAt(i);
            str.append(ch);
            i++;
        }

        while(j<word2.length()){
            char ch = word2.charAt(j);
            str.append(ch);
            j++;
        }
        return str.toString();
    }
}