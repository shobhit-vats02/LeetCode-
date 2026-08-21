class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // ASCII characters
        int[] mapST = new int[256];
        int[] mapTS = new int[256];

        for (int i = 0; i < s.length(); i++) {
            int a = s.charAt(i);
            int b = t.charAt(i);

            if (mapST[a] != 0 && mapST[a] != b + 1) {
                return false;
            }

            if (mapTS[b] != 0 && mapTS[b] != a + 1) {
                return false;
            }

            mapST[a] = b + 1;
            mapTS[b] = a + 1;
        }

        return true;
    }
}