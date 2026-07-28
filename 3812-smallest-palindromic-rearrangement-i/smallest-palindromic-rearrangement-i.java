class Solution {
    public String smallestPalindrome(String s) {
        int len = s.length();
        
        String half = s.substring(0,len/2);

        char[] ch = half.toCharArray();

        Arrays.sort(ch);
        String sortedHalf = new String(ch);

        String middle = "";

        if(len%2==1){
            middle = String.valueOf(s.charAt(len/2));
        }

        String reverseSortedHalf = new StringBuilder(sortedHalf).reverse().toString();





        return sortedHalf+middle+reverseSortedHalf;
    }
}