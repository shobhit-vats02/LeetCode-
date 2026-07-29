class Solution {
    long LIMIT = 1000001L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        String mid = "";
        ArrayList<Integer> half = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) mid = String.valueOf((char) ('a' + i));
            for (int j = 0; j < freq[i] / 2; j++) half.add(i);
        }

        int m = half.size();
        int[] cnt = new int[26];
        for (int x : half) cnt[x]++;

        long[][] C = new long[m + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            C[i][0] = C[i][i] = 1;
            for (int j = 1; j < i; j++) {
                C[i][j] = Math.min(LIMIT, C[i - 1][j - 1] + C[i - 1][j]);
            }
        }

        long total = ways(cnt, m, C);
        if (total < k) return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < m; pos++) {
            for (int c = 0; c < 26; c++) {
                if (cnt[c] == 0) continue;

                cnt[c]--;
                long w = ways(cnt, m - pos - 1, C);

                if (k > w) {
                    k -= w;
                    cnt[c]++;
                } else {
                    left.append((char) ('a' + c));
                    break;
                }
            }
        }

        String right = new StringBuilder(left).reverse().toString();
        return left.toString() + mid + right;
    }

    private long ways(int[] cnt, int total, long[][] C) {
        long res = 1;
        int rem = total;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) continue;
            res = Math.min(LIMIT, res * C[rem][cnt[i]]);
            rem -= cnt[i];
            if (res >= LIMIT) return LIMIT;
        }

        return Math.min(res, LIMIT);
    }
}