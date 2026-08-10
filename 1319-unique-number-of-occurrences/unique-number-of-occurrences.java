class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n : arr){
            map.put(n,map.getOrDefault(n,0)+1);
        } 

        HashSet<Integer> set = new HashSet<>();
        for(int value : map.keySet() ){
            int freq = map.get(value);
            if(set.contains(freq)){
                return false;
            }
            set.add(freq);
        }
        return true;
    }
}