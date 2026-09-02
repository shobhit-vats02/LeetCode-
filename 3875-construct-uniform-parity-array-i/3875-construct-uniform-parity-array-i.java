class Solution {
    public boolean uniformArray(int[] nums1) {
        boolean hasOdd = false;
        boolean hasEven = false;

        for (int x : nums1) {
            if (x % 2 == 0)
                hasEven = true;
            else
                hasOdd = true;
        }

        // If all elements already have the same parity
        if (!hasOdd || !hasEven)
            return true;

        // If both parities exist, choose an odd element as a
        // subtractor for every even element.
        // For odd elements, keep them unchanged.
        return hasOdd;
    }
}