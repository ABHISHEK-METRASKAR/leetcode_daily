class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int arriveAliceDay = calculateDayOfYear(arriveAlice, daysInMonths);
        int leaveAliceDay = calculateDayOfYear(leaveAlice, daysInMonths);
        int arriveBobDay = calculateDayOfYear(arriveBob, daysInMonths);
        int leaveBobDay = calculateDayOfYear(leaveBob, daysInMonths);
        
        int start = Math.max(arriveAliceDay, arriveBobDay);
        int end = Math.min(leaveAliceDay, leaveBobDay);
        
        return Math.max(0, end - start + 1);
    }
    
    private int calculateDayOfYear(String date, int[] daysInMonths) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonths[i];
        }
        return totalDays;
    }
}