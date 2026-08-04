class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }

            if (nums[i] < min) {
                min = nums[i];
            }
        }

       

        HashSet<Integer> set1 = new HashSet<>();
        for(int i=0; i<nums.length; i++){//1 2 3 4 5 6 7
            set1.add(nums[i]);
        }

        List<Integer> list = new ArrayList<>();
        for(int i=min; i<max; i++){
            if(!set1.contains(i)){
                list.add(i);
            }
        }
        return list;
    }
}