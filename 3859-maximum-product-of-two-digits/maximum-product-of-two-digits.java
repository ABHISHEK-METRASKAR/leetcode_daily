class Solution {
    public int maxProduct(int n) {
        
        int max = Integer.MIN_VALUE;
        int secMax = Integer.MIN_VALUE;

        while(n!=0){

            int rem = n%10;

            if(rem>max){
                secMax = max;
                max = rem;
            }
            else if(rem>secMax){
                secMax = rem;
            }
            n/=10;
        }
        return max*secMax;
    }
}