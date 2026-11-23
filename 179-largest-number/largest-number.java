class Solution {
    public String largestNumber(int[] nums) {
        StringBuilder result = new StringBuilder();

        String[] arr = new String[nums.length];

        for(int i=0; i<nums.length; i++){
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr, (a,b) -> (b+a).compareTo(a+b));

        if(arr[0].equals("0")){
            return "0";
        }

        for(String s : arr){
            result.append(s);
        }

        return result.toString();
    }
    
}