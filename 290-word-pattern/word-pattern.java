class Solution {
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character,String> mapPS = new HashMap<>();
        HashMap<String,Character> mapSP = new HashMap<>();
        
        String[] arr = s.split(" ");

        if(pattern.length()!=arr.length) return false;

        for(int i=0; i<pattern.length(); i++){
            char c = pattern.charAt(i);
            String s1 = arr[i];

            if(mapPS.containsKey(c)){
                if(!mapPS.get(c).equals(s1)){
                    return false;
                }
            }
            mapPS.put(c,s1);

            if(mapSP.containsKey(s1)){
                if(!mapSP.get(s1).equals(c)){
                    return false;
                }
            }
            mapSP.put(s1,c);
        }  
        return true;      
    }
}