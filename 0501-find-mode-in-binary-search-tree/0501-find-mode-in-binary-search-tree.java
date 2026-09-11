class Solution {
    int maxFreq = 0, currFreq = 0;
    Integer prev = null;
    java.util.ArrayList<Integer> modes = new java.util.ArrayList<>();

    public int[] findMode(TreeNode root) {
        inorder(root);

        int[] ans = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            ans[i] = modes.get(i);
        }

        return ans;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        if (prev != null && prev == node.val) {
            currFreq++;
        } else {
            currFreq = 1;
        }

        if (currFreq > maxFreq) {
            maxFreq = currFreq;
            modes.clear();
            modes.add(node.val);
        } else if (currFreq == maxFreq) {
            modes.add(node.val);
        }

        prev = node.val;

        inorder(node.right);
    }
}