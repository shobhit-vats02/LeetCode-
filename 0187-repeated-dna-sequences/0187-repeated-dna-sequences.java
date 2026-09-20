class Solution {
    public java.util.List<String> findRepeatedDnaSequences(String s) {
        java.util.List<String> ans = new java.util.ArrayList<>();

        if (s.length() < 10) return ans;

        java.util.HashMap<String, Integer> freq = new java.util.HashMap<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            freq.put(sub, freq.getOrDefault(sub, 0) + 1);
        }

        for (java.util.Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > 1) {
                ans.add(entry.getKey());
            }
        }

        return ans;
    }
}