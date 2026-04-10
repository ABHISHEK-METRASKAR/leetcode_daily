class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String,Integer> map = new HashMap<>();

        for(String s : words){
            map.put(s,map.getOrDefault(s,0)+1);
        }

        PriorityQueue<String> pq = new PriorityQueue<>(
            (a, b) -> {
                if(map.get(a).equals(map.get(b))){
                    return b.compareTo(a); 
                }
                return map.get(a) - map.get(b); 
            }
        );


        for(String n :map.keySet()){
            pq.add(n);

            if(pq.size()>k){
                pq.poll();
            }
        }
        LinkedList<String> list = new LinkedList<>();
    
        while(!pq.isEmpty()){
            list.addFirst(pq.poll());            
        }
        return list;
    }
}