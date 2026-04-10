class Solution {
    public String frequencySort(String s) {
        HashMap<Character,Integer> map = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            map.put(c,map.getOrDefault(c,0)+1);
        }

        PriorityQueue<Character> pq = new PriorityQueue<>(
            (a,b)->{
                if(map.get(a).equals(map.get(b))){
                    return b.compareTo(a);
                }
                return map.get(b) - map.get(a);
            }
        );

        for(char key: map.keySet()){
            pq.add(key);
            
        }

        StringBuilder result = new StringBuilder();
        while (!pq.isEmpty()) {
            char ch = pq.poll();
            int freq = map.get(ch);

            for (int i = 0; i < freq; i++) {
                result.append(ch);
            }
        }

        return result.toString();
    }
}