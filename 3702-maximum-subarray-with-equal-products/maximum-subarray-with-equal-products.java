class Solution {
    public int maxLength(int[] nums) {
          int maxNum = 0;
        int maxLcm = 1;

        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
            maxLcm = lcm(maxLcm, num);
        }

        int maxProduct = maxNum * maxLcm;
        int maxLen = 0;

        for (int left = 0; left < nums.length; left++) {
            int product = 1;
            int gcd = 0;
            int lcm = 1;

            for (int right = left; right < nums.length; right++) {
                product *= nums[right];
                gcd = gcd(gcd, nums[right]);
                lcm = lcm(lcm, nums[right]);

                if (product == gcd * lcm) {
                    maxLen = Math.max(maxLen, right - left + 1);
                }

                if (product > maxProduct) {
                    break;
                }
            }
        }

        return maxLen;
    }

   

    int gcd(int a, int b){
        while(b != 0){
            int temp  = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    int findLcm(int[] arr, int left, int right){
        int result = arr[left];
        for(int i=left+1; i<=right; i++){
            result = lcm(result,arr[i]);

            if(result == 0){
                return 0;
            }
        }
        return result;
    }

    int lcm(int a, int b){
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }


}