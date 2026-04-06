class Solution {
    public List<Integer> findAnagrams(String s, String p) {
      ArrayList<Integer> list = new ArrayList<>();

      HashMap<Character,Integer> mapS = new HashMap<>();
      HashMap<Character,Integer> mapP = new HashMap<>();

        for(char c : p.toCharArray()){
          mapP.put(c,mapP.getOrDefault(c,0)+1);
        }
        
        int k = p.length();

        for(int i=0; i<s.length(); i++){
            char right = s.charAt(i);
            mapS.put(right,mapS.getOrDefault(right,0)+1);

            if(i>=k){
                char left = s.charAt(i-k);
                mapS.put(left,mapS.get(left)-1);

                if(mapS.get(left)==0){
                mapS.remove(left);
                }
            }

            

            if(mapS.equals(mapP)){
                list.add(i-k+1);
            }
            

        } 
        return list;
    }
}