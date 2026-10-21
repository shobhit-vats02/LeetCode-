class Solution {
    int abc(Map<Integer, Integer> x, TreeNode r) {
        if (r == null) return 0;
        int a = abc(x, r.left);
        int b = abc(x, r.right);
        x.merge(a + b + r.val, 1, Integer::sum);
        return a + b + r.val;
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> x = new HashMap<>();
        abc(x, root);
        int mx = 1;
        for (int c : x.values()) mx = Math.max(mx, c);
        List<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : x.entrySet()) if (e.getValue() == mx) ans.add(e.getKey());
        int[] res = new int[ans.size()];
        for (int i = 0; i < res.length; i++) res[i] = ans.get(i);
        return res;
    }
}