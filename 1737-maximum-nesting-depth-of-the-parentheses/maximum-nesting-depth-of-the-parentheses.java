class Solution {
    public int maxDepth(String s) {
        int currMax = 0;
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) =='('){
                count++;
                currMax = Math.max(currMax, count);
            }
            else if(s.charAt(i)==')'){
                count--;
            }
        }
        return currMax;
    }
}