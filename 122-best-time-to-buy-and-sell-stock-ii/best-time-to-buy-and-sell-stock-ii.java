class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int n = prices.length;

        int left = 0;
        while(left < n-1){

            while(left < n-1 && prices[left] >= prices[left+1]){
                left++;
            }

            int right = left;

            while(right < n-1 && prices[right] <= prices[right+1]){
                right++;
            }

            profit += prices[right] - prices[left];
            left = right+1;
        }

        return profit;
    }
}