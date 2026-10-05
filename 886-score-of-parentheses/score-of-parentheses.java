class Solution {
    public int scoreOfParentheses(String s) {

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(0);
            } else {
                int innerScore = stack.pop();
                int score = (innerScore == 0) ? 1 : 2 * innerScore;

                stack.push(stack.pop() + score);
            }
        }

        return stack.pop();
    }
}