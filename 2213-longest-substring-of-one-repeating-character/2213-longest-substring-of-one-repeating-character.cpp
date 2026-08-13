class Solution {
public:
    struct Node {
        int len;
        int prefix, suffix, best;
        char leftChar, rightChar;

        Node() {
            len = prefix = suffix = best = 0;
            leftChar = rightChar = 0;
        }

        Node(char c) {
            len = prefix = suffix = best = 1;
            leftChar = rightChar = c;
        }
    };

    vector<Node> tree;

    Node merge(Node a, Node b) {
        if (a.len == 0) return b;
        if (b.len == 0) return a;

        Node res;
        res.len = a.len + b.len;
        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        res.prefix = a.prefix;
        if (a.prefix == a.len && a.rightChar == b.leftChar)
            res.prefix = a.len + b.prefix;

        res.suffix = b.suffix;
        if (b.suffix == b.len && a.rightChar == b.leftChar)
            res.suffix = b.len + a.suffix;

        res.best = max(a.best, b.best);

        if (a.rightChar == b.leftChar)
            res.best = max(res.best, a.suffix + b.prefix);

        return res;
    }

    void build(int node, int l, int r, const string& s) {
        if (l == r) {
            tree[node] = Node(s[l]);
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, s);
        build(node * 2 + 1, mid + 1, r, s);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int l, int r, int pos, char c) {
        if (l == r) {
            tree[node] = Node(c);
            return;
        }

        int mid = (l + r) / 2;

        if (pos <= mid)
            update(node * 2, l, mid, pos, c);
        else
            update(node * 2 + 1, mid + 1, r, pos, c);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    vector<int> longestRepeating(string s, string queryCharacters,
                                 vector<int>& queryIndices) {
        int n = s.size();

        tree.resize(4 * n);

        build(1, 0, n - 1, s);

        vector<int> ans;

        for (int i = 0; i < queryIndices.size(); i++) {
            int index = queryIndices[i];
            char c = queryCharacters[i];

            update(1, 0, n - 1, index, c);

            ans.push_back(tree[1].best);
        }

        return ans;
    }
};