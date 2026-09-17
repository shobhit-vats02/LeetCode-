class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] freq = new int[1001];
        
        for (int x : nums1) {
            freq[x]++;
        }
        
        int[] temp = new int[Math.min(nums1.length, nums2.length)];
        int k = 0;
        
        for (int x : nums2) {
            if (freq[x] > 0) {
                temp[k++] = x;
                freq[x]--;
            }
        }
        
        return java.util.Arrays.copyOf(temp, k);
    }
}