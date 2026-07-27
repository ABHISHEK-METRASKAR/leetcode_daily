class Solution {
    public long sumAndMultiply(int n) {

        if(n==0){
            return 0;
        }
        String s = String.valueOf(n);
        String s1 = s.replace("0",""); 

        long result = Long.parseLong(s1);

        long num = result;
        long sum = 0;

        while(num!=0){
            long rem = num%10;
            sum +=rem;
            num/=10;
        }

        return result*sum;
    }
}