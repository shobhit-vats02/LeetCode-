class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int leftSum = 0;
        int rightSum = 0;
        int leftQ = 0;
        int rightQ = 0;

        for (int i = 0; i < half; i++) {
            if (num.charAt(i) == '?')
                leftQ++;
            else
                leftSum += num.charAt(i) - '0';
        }

        for (int i = half; i < n; i++) {
            if (num.charAt(i) == '?')
                rightQ++;
            else
                rightSum += num.charAt(i) - '0';
        }

        // Difference in the current known sums
        int diff = leftSum - rightSum;

        // If number of '?' is odd, Alice gets the extra move
        if ((leftQ + rightQ) % 2 == 1) {
            return true;
        }

        // Alice can force inequality unless the difference can
        // be perfectly compensated by the '?' positions.
        return Math.abs(diff + (leftQ - rightQ) * 9 / 2) != 0;
    }
}