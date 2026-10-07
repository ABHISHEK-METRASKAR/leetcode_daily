class Solution {

    Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int leftRemove = 0;
        int rightRemove = 0;

        // Find minimum number of '(' and ')' to remove
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                leftRemove++;
            } 
            else if (ch == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                } 
                else {
                    rightRemove++;
                }
            }
        }

        dfs(s, 0, 0, leftRemove, rightRemove, new StringBuilder());

        return new ArrayList<>(result);
    }

    private void dfs(
            String s,
            int index,
            int balance,
            int leftRemove,
            int rightRemove,
            StringBuilder current) {

        // Invalid balance
        if (balance < 0) {
            return;
        }

        // End of string
        if (index == s.length()) {

            if (balance == 0 &&
                leftRemove == 0 &&
                rightRemove == 0) {

                result.add(current.toString());
            }

            return;
        }

        char ch = s.charAt(index);

        // Parenthesis
        if (ch == '(') {

            // Option 1: Remove '('
            if (leftRemove > 0) {
                dfs(
                    s,
                    index + 1,
                    balance,
                    leftRemove - 1,
                    rightRemove,
                    current
                );
            }

            // Option 2: Keep '('
            current.append(ch);

            dfs(
                s,
                index + 1,
                balance + 1,
                leftRemove,
                rightRemove,
                current
            );

            current.deleteCharAt(current.length() - 1);
        }

        else if (ch == ')') {

            // Option 1: Remove ')'
            if (rightRemove > 0) {
                dfs(
                    s,
                    index + 1,
                    balance,
                    leftRemove,
                    rightRemove - 1,
                    current
                );
            }

            // Option 2: Keep ')' only if balance > 0
            if (balance > 0) {

                current.append(ch);

                dfs(
                    s,
                    index + 1,
                    balance - 1,
                    leftRemove,
                    rightRemove,
                    current
                );

                current.deleteCharAt(current.length() - 1);
            }
        }

        else {
            // Normal character
            current.append(ch);

            dfs(
                s,
                index + 1,
                balance,
                leftRemove,
                rightRemove,
                current
            );

            current.deleteCharAt(current.length() - 1);
        }
    }
}