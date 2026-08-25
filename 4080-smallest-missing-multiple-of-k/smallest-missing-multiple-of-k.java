class Solution {
    public int missingMultiple(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();

        for(int num : nums){
            set.add(num);
        }

        int multK = k;
        while(set.contains(multK)){
            multK += k; 
        }

        return multK;
        
    }
}