class Solution {

    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.index = 0;

        Set<String> result = parse();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse() {
        Set<String> result = new HashSet<>();

        Set<String> current = new HashSet<>();
        current.add("");

        while (index < expression.length() && expression.charAt(index) != '}') {

            char ch = expression.charAt(index);

            if (ch == ',') {
                result.addAll(current);

                current = new HashSet<>();
                current.add("");

                index++;

            } else {
                Set<String> next;

                if (ch == '{') {
                    index++;
                    next = parse();
                    index++;
                } else {
                    next = new HashSet<>();
                    next.add(String.valueOf(ch));
                    index++;
                }

                current = concatenate(current, next);
            }
        }

        result.addAll(current);

        return result;
    }

    private Set<String> concatenate(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}