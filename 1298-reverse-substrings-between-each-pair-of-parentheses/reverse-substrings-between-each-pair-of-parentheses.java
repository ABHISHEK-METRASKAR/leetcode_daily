class Solution {
    public String reverseParentheses(String s) {

        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '(') {
                stack.push(current);
                current = new StringBuilder();
            }

            else if (ch == ')') {
                current.reverse();

                StringBuilder previous = stack.pop();
                previous.append(current);

                current = previous;
            }

            else {
                current.append(ch);
            }
        }

        return current.toString();
    }
}