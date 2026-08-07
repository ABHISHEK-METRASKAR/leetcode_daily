class Solution {
    public void moveZeroes(int[] nums) {
        int left = 0;

        for(int right=0; right<nums.length; right++){
            if(nums[right] != 0 ){
                swap(nums,left,right);
                left++;
            }
        }
   
    }
    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
   
}