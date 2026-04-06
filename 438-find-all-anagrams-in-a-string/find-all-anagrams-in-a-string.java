class Solution {
    public List<Integer> findAnagrams(String s, String p) {
      ArrayList<Integer> list = new ArrayList<>();

      int[] mapP = new int[26];
      int[] mapS = new int[26];

      for(char c: p.toCharArray()){
        mapP[c-'a']++;
      }

      int k = p.length();

      for(int i=0;i<s.length();i++){
        mapS[s.charAt(i)-'a']++;

        if(i>=k){
            mapS[s.charAt(i-k)-'a']--;
        }
        if(Arrays.equals(mapS,mapP)){
            list.add(i-k+1);
        }
      }
            

     
        return list;
    }
}