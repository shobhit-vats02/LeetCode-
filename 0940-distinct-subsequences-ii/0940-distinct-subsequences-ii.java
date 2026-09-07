class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1000000007L;

        long[] last = new long[26];
        long total = 1; // includes empty subsequence

        for (char c : s.toCharArray()) {
            int idx = c - 'a';

            long newTotal = (2 * total - last[idx] + MOD) % MOD;

            last[idx] = total;
            total = newTotal;
        }

        // Remove the empty subsequence
        return (int) ((total - 1 + MOD) % MOD);
    }
}