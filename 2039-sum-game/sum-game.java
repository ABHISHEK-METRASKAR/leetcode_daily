class Solution {
    public boolean sumGame(String num) {

        char[] charArr = num.toCharArray();
        int n = charArr.length;

        int leftSum = 0;
        int rightSum = 0;
        int leftQ = 0;
        int rightQ = 0;

        for(int i=0; i<n/2; i++){
            if(charArr[i]=='?'){
                leftQ++;
            }
            else{
                leftSum += charArr[i]-'0';
            }
        }

        for(int i=n/2; i<n; i++){
            if(charArr[i]=='?'){
                rightQ++;
            }
            else{
                rightSum += charArr[i]-'0';
            }
        }
      
        int sumDiff = leftSum-rightSum;
        int QDiff = rightQ-leftQ;

        if(2*sumDiff == 9*QDiff){
            return false;
        }
        return true;

    }
}