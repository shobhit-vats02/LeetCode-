class Solution {
public:
    bool winnerSquareGame(int n) {
        vector<bool> dp(n + 1, false);

        // dp[i] = true if the current player can force a win
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                // If we can move to a losing position,
                // the current player wins.
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
};