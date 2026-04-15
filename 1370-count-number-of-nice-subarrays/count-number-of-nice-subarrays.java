class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums,k) - atMost(nums,k-1);
    }

    public static int atMost(int[] nums,int k){
        int left = 0;
        int count = 0;
        int oddCount = 0;

        for(int right=0; right<nums.length; right++){
            if(nums[right]%2==1){
                oddCount++;
            }

            while(oddCount>k){
                if(nums[left]%2==1){
                    oddCount--;
                }
                left++;
            }

            count += right-left+1;
        }
        return count;
    }
}