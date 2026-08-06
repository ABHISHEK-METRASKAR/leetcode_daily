class Solution {
    public String reverseVowels(String s) {
        char[] ch = s.toCharArray();
        int left = 0;
        int right = s.length()-1;

        while(left<right){
            
            while(left<right && !isVowel(ch[left])){
                left++;
            }

            while(left<right && !isVowel(ch[right])){
                right--;
            }

            char temp = ch[left];
            ch[left] = ch[right];
            ch[right] = temp;

            left++;
            right--;

        }
        return new String(ch);
    }

    private boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' ||
               ch == 'o' || ch == 'u';
    }
}