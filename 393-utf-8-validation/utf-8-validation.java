class Solution {
    public boolean validUtf8(int[] data) {
        int remaining = 0;

        for (int num : data) {

            // Keep only last 8 bits
            num = num & 255;

            if (remaining == 0) {

                // 1-byte character
                if ((num >> 7) == 0) {
                    continue;
                }

                // 2-byte character
                else if ((num >> 5) == 0b110) {
                    remaining = 1;
                }

                // 3-byte character
                else if ((num >> 4) == 0b1110) {
                    remaining = 2;
                }

                // 4-byte character
                else if ((num >> 3) == 0b11110) {
                    remaining = 3;
                }

                else {
                    return false;
                }
            }

            // Continuation byte must start with 10
            else {
                if ((num >> 6) != 0b10) {
                    return false;
                }

                remaining--;
            }
        }

        return remaining == 0;
    }
}