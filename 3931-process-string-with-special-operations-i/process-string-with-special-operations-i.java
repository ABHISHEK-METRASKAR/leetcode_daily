class Solution {
    public String processStr(String s) {

        Stack<Character> result = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            switch(ch){

                case '*':
                        {
                            if (!result.isEmpty()) {
                                result.pop();
                            }
                        }
                        break;
                
                case '#':
                        {
                            Stack<Character> currentCopy = new Stack<>();
                            currentCopy.addAll(result);
                            result.addAll(currentCopy);
                        }
                        break;

                case '%':
                        {
                            Collections.reverse(result);
                        }
                        break;
                
                default : result.push(ch);
            }
        }
        StringBuilder r = new StringBuilder();
        for (char c : result) {
            r.append(c);
        }
        return r.toString();
    }
}