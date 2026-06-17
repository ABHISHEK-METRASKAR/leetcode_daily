class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n + 1];

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                len[i + 1] = len[i] + 1;
            } else if (ch == '*') {
                len[i + 1] = Math.max(0, len[i] - 1);
            } else if (ch == '#') {
                len[i + 1] = Math.min(Long.MAX_VALUE, len[i] * 2);
            } else {
                len[i + 1] = len[i];
            }
        }

        if (k >= len[n]) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            long prev = len[i];

            if (ch >= 'a' && ch <= 'z') {
                if (k == prev) {
                    return ch;
                }
            } else if (ch == '#') {
                if (k >= prev) {
                    k -= prev;
                }
            } else if (ch == '%') {
                k = prev - 1 - k;
            }
        }

        return '.';
    }
}