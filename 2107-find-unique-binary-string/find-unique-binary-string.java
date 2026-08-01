class Solution {
    public String findDifferentBinaryString(String[] nums) {
        HashSet<String> set = new HashSet<>();
        HashSet<String> givenSet = new HashSet<>();

        int n = nums.length;

        int combinations = 1<<n;

        for(int i=0; i<combinations; i++){

            String binary = Integer.toBinaryString(i);

            String padded = String.format("%"+ n + "s", binary).replace(" " ,"0");

            if(padded.contains("1")){
                set.add(padded);
            }
        }

        for(int s=0; s<nums.length; s++){
            givenSet.add(nums[s]);
        }

        for(String key : set){
            if(!givenSet.contains(key)){
                return key;
            }
        }
        return "0";
    }
}