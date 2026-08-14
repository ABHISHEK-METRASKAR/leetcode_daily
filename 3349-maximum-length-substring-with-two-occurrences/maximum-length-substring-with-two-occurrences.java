class Solution {
    public int maximumLengthSubstring(String s) {

        HashMap<Character, Integer> map = new HashMap<>();
        int length = s.length();
        int max = 0;
        int left = 0;
        int right = 0;

        while(left < length){
            char leftCh = s.charAt(left);
            map.put(leftCh,map.getOrDefault(leftCh,0)+1);
            while(map.get(leftCh) > 2){
                char rightCh = s.charAt(right);
                map.put(rightCh,map.get(rightCh)-1);
                right++;
            }
            max = Math.max(max, left-right+1);
            left++;
        }
        return max;
    }
}