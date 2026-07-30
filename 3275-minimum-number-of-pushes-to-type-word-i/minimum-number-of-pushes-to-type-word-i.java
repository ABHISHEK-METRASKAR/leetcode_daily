class Solution {
    public int minimumPushes(String word) {
        
        int n = word.length();

        int pushed  = 0;

        for(int i=0; i<n; i++){
            pushed += (i/8)+1;
        }
        return pushed;
    }
}