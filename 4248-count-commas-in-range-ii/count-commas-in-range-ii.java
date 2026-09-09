class Solution {
    public long countCommas(long n) {
        long lowerBound = 1000;
        long countComm = 0;

        while(lowerBound <= n){
            countComm += (n-lowerBound+1);

            lowerBound *= 1000;
        }


       return countComm;
    }
}