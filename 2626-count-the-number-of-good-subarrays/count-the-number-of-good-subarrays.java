class Solution {
    public long countGood(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();

        int left = 0;
        long pairs = 0;
        long count = 0;

        for(int right=0; right<nums.length; right++){
            int val = nums[right];

            pairs += map.getOrDefault(val,0);

            map.put(val,map.getOrDefault(val,0)+1);

            while(pairs>=k){
                count += nums.length - right;

                int leftNum = nums[left];
                map.put(leftNum,map.get(leftNum)-1);
                pairs -= map.get(leftNum);
                left++;
            }
        }
        return count;
    }
}