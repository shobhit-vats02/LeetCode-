class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Check whether a palindrome is possible.
        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = n / 2;
        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        // Build the palindrome from a given first half.
        String makePalindrome = "";

        /*
         * First check if we can use exactly target's first half.
         * This matters because if the first half is equal to target's
         * first half, the remaining palindrome might already be > target.
         */
        if (canForm(target, halfFreq, halfLen)) {
            String half = target.substring(0, halfLen);
            String candidate = build(half, middle, n);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        /*
         * Find the smallest half that is lexicographically greater
         * than target's first half.
         */
        for (int pivot = halfLen - 1; pivot >= 0; pivot--) {

            int[] remaining = halfFreq.clone();

            // Match target's prefix before pivot.
            boolean possible = true;

            for (int i = 0; i < pivot; i++) {
                int c = target.charAt(i) - 'a';

                if (remaining[c] == 0) {
                    possible = false;
                    break;
                }

                remaining[c]--;
            }

            if (!possible) {
                continue;
            }

            int targetChar = target.charAt(pivot) - 'a';

            // Choose the smallest character greater than target[pivot].
            for (int c = targetChar + 1; c < 26; c++) {
                if (remaining[c] == 0) {
                    continue;
                }

                StringBuilder half = new StringBuilder();

                // Prefix equal to target.
                for (int i = 0; i < pivot; i++) {
                    half.append(target.charAt(i));
                }

                // Make the pivot character larger.
                half.append((char) ('a' + c));
                remaining[c]--;

                // Smallest possible suffix.
                for (int x = 0; x < 26; x++) {
                    while (remaining[x] > 0) {
                        half.append((char) ('a' + x));
                        remaining[x]--;
                    }
                }

                return build(half.toString(), middle, n);
            }
        }

        return "";
    }

    private boolean canForm(String target, int[] halfFreq, int halfLen) {
        int[] need = new int[26];

        for (int i = 0; i < halfLen; i++) {
            need[target.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (need[i] != halfFreq[i]) {
                return false;
            }
        }

        return true;
    }

    private String build(String half, int middle, int n) {
        StringBuilder ans = new StringBuilder();

        ans.append(half);

        if (n % 2 == 1) {
            ans.append((char) ('a' + middle));
        }

        for (int i = half.length() - 1; i >= 0; i--) {
            ans.append(half.charAt(i));
        }

        return ans.toString();
    }
}