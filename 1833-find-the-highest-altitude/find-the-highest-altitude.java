class Solution {
    public int largestAltitude(int[] gain) {
        int n = gain.length;
        int[] prefixSum = new int[n+1];

        prefixSum[0] = 0;

        for(int i=0; i<n; i++){
            prefixSum[i+1] = prefixSum[i] + gain[i];
        }

        int highest = 0;
        for(int i=0; i<prefixSum.length; i++){
            if(prefixSum[i]>=highest){
                highest = prefixSum[i];
            }
        }
        return highest;
    }
}