class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        boolean[] present = new boolean[1001];
        boolean[] added = new boolean[1001];

        for (int x : nums1) {
            present[x] = true;
        }

        int[] temp = new int[Math.min(nums1.length, nums2.length)];
        int k = 0;

        for (int x : nums2) {
            if (present[x] && !added[x]) {
                temp[k++] = x;
                added[x] = true;
            }
        }

        return java.util.Arrays.copyOf(temp, k);
    }
}