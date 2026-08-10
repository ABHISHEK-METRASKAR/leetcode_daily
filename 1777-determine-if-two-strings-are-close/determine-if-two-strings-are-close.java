class Solution {
    public boolean closeStrings(String word1, String word2) {
        if(word1.length() != word2.length()){
            return false;
        }

        HashMap<Character, Integer> map1 = new HashMap<>();
        for(int i=0; i<word1.length(); i++){
            char word = word1.charAt(i);
            map1.put(word,map1.getOrDefault(word,0)+1);
        }

        HashMap<Character, Integer> map2 = new HashMap<>();
        for(int i=0; i<word2.length(); i++){
            char word = word2.charAt(i);
            map2.put(word,map2.getOrDefault(word,0)+1);
        }

        if(!map1.keySet().equals(map2.keySet())){
            return false;
        }

        ArrayList<Integer> frq1 = new ArrayList<>(map1.values());
        ArrayList<Integer> frq2 = new ArrayList<>(map2.values());

        Collections.sort(frq1);
        Collections.sort(frq2);

        return frq1.equals(frq2);
    }
}