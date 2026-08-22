class Solution {
    public boolean checkDivisibility(int n) {
        int sum  = 0;
        int prod = 1;
        int num = n;

        if(n==0){
            return false;
        }

        while(num != 0){
            int rem = num % 10;
            sum = sum + rem;
            prod = prod * rem;
            num = num / 10;
        }

        if(sum==0){
            return false;
        }
        int sumProd = sum + prod;
        return n % sumProd == 0;
    }
}