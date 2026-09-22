class Solution {
    static class Node {
        long product;
        long[] pref;

        Node(int k) {
            pref = new long[k];
        }
    }

    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        int n = nums.length;

        tree = new Node[4 * n];
        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);
            ans[q] = (int) res.pref[x];
        }

        return ans;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            tree[node] = new Node(k);
            int rem = nums[l] % k;

            tree[node].product = rem;
            tree[node].pref[rem] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int index, int value) {
        if (l == r) {
            tree[node] = new Node(k);

            int rem = value % k;
            tree[node].product = rem;
            tree[node].pref[rem] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid)
            update(node * 2, l, mid, index, value);
        else
            update(node * 2 + 1, mid + 1, r, index, value);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        if (qr <= mid)
            return query(node * 2, l, mid, ql, qr);

        if (ql > mid)
            return query(node * 2 + 1, mid + 1, r, ql, qr);

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    private Node merge(Node left, Node right) {
        Node res = new Node(k);

        res.product = (left.product * right.product) % k;

        // Prefixes entirely inside the left part
        for (int x = 0; x < k; x++) {
            res.pref[x] += left.pref[x];
        }

        // Prefixes that use the entire left part
        // and then a prefix of the right part.
        for (int x = 0; x < k; x++) {
            if (right.pref[x] == 0) continue;

            int newRem = (int) ((left.product * x) % k);
            res.pref[newRem] += right.pref[x];
        }

        return res;
    }
}