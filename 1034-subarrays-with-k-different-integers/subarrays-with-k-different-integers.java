class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums,k)-atMost(nums,k-1);
    }

    public static int atMost(int[] nums,int k){
        HashMap<Integer,Integer> map =  new HashMap<>();
        int left = 0;
        int count = 0;

        for(int right=0; right<nums.length; right++){
            int num = nums[right];

            map.put(num,map.getOrDefault(num,0)+1);

            while(map.size()>k){
                int leftNum = nums[left];
                map.put(leftNum,map.get(leftNum)-1);

                if(map.get(leftNum)==0){
                    map.remove(leftNum);
                }
                left++;   
            }
            count += right-left+1;
            
        }
        return count;
    }
}