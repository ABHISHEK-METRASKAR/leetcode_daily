class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int ans = Integer.MAX_VALUE;

        int n = landStartTime.length;
        int m = waterStartTime.length;

        // Land -> Water
        for (int i = 0; i < n; i++) {
            int landFinish = landStartTime[i] + landDuration[i];

            for (int j = 0; j < m; j++) {
                int waterBegin = Math.max(landFinish, waterStartTime[j]);
                ans = Math.min(ans, waterBegin + waterDuration[j]);
            }
        }

        // Water -> Land
        for (int i = 0; i < m; i++) {
            int waterFinish = waterStartTime[i] + waterDuration[i];

            for (int j = 0; j < n; j++) {
                int landBegin = Math.max(waterFinish, landStartTime[j]);
                ans = Math.min(ans, landBegin + landDuration[j]);
            }
        }

        return ans;
    }
}