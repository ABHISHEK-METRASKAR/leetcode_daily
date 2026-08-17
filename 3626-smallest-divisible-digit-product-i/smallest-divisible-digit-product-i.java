class Solution {
    public int smallestNumber(int n, int t) {
        int ans = 0;
        for(int i=n; i>=n; i++){
            int prod = 1;
            int num = i;
            while(num != 0){
                int rem = num%10;
                prod = prod * rem;
                num /= 10;
            }
            if(prod%t== 0){
                ans = i;
                break;
            }

        }
        return ans;
    }
}