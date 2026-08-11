class Solution {
    public String removeStars(String s) {
       Stack<Character> stack  = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(ch == '*'){
                stack.pop();
            }
            else{
                stack.push(ch);
            }

            
        }
        StringBuilder result = new StringBuilder();
        for(char ch : stack){
            result.append(ch);
        }
        String ans = result.toString();
        return ans;
    }
}