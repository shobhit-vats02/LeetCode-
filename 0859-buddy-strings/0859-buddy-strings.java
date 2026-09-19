class Solution {
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) return false;

        int first = -1, second = -1;
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;

            if (s.charAt(i) != goal.charAt(i)) {
                if (first == -1) first = i;
                else if (second == -1) second = i;
                else return false;
            }
        }

        // Strings are already equal: need a duplicate character
        // so swapping two equal characters actually counts.
        if (first == -1) {
            for (int count : freq) {
                if (count >= 2) return true;
            }
            return false;
        }

        // Must have exactly two mismatched positions.
        if (second == -1) return false;

        return s.charAt(first) == goal.charAt(second)
            && s.charAt(second) == goal.charAt(first);
    }
}