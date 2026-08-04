class Solution {
    public boolean rotateString(String s, String goal) {
        int n = s.length();

        if(s.length() != goal.length()){
            return false;
        }
        
        HashSet<String> set = new HashSet<>();
        for(int i=0; i<n; i++){
            s = s.substring(1) + s.charAt(0);
            set.add(s);
        }

        
        return set.contains(goal);


    }
}