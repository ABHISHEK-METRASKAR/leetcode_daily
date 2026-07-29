class Solution {
    public int[] separateDigits(int[] nums) {
        
         List<Integer> list = new ArrayList<>(); 

        for(int i=nums.length-1; i>=0; i--){
            
            int temp = nums[i];

            while(temp>0){      

                list.add(0,temp%10);
                temp/=10;

            }
        }

        int[] result = new int[list.size()];

        for(int i=0; i<list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }
}