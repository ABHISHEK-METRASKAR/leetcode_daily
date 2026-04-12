class Solution {
    public int[] singleNumber(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] res = new int[2];

        for(int num: nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        int i= 0;
        for(int num : map.keySet()){
            if(map.get(num)==1){
                res[i] = num;
                i++;
                if(i==2){
                    return res;
                }
            }
        }
        return res;
    }
}