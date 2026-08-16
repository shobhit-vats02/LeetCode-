class Solution:
    def connect(self, root: 'Node') -> 'Node':
        if not root:
            return None

        leftmost = root

        while leftmost:
            curr = leftmost
            leftmost = None
            prev = None

            while curr:
                # Connect left child
                if curr.left:
                    if prev:
                        prev.next = curr.left
                    else:
                        leftmost = curr.left
                    prev = curr.left

                # Connect right child
                if curr.right:
                    if prev:
                        prev.next = curr.right
                    else:
                        leftmost = curr.right
                    prev = curr.right

                curr = curr.next

            # Last node of the next level
            if prev:
                prev.next = None

        return root