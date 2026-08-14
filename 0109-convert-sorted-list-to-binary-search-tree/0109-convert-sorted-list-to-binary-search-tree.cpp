class Solution {
public:
    TreeNode* sortedListToBST(ListNode* head) {
        if (head == nullptr)
            return nullptr;

        // Single node
        if (head->next == nullptr)
            return new TreeNode(head->val);

        // Find middle node
        ListNode* slow = head;
        ListNode* fast = head;
        ListNode* prev = nullptr;

        while (fast != nullptr && fast->next != nullptr) {
            prev = slow;
            slow = slow->next;
            fast = fast->next->next;
        }

        // Disconnect left half from middle
        prev->next = nullptr;

        TreeNode* root = new TreeNode(slow->val);

        // Left subtree
        root->left = sortedListToBST(head);

        // Right subtree
        root->right = sortedListToBST(slow->next);

        return root;
    }
};