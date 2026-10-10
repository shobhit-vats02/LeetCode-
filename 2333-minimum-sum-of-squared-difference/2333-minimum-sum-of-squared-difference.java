class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] freq = new long[100001];
        long operations = (long) k1 + k2;
        long totalDiff = 0;

        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
            totalDiff += diff;
        }

        if (operations >= totalDiff) {
            return 0;
        }

        for (int d = 100000; d > 0 && operations > 0; d--) {
            long count = freq[d];
            if (count == 0) continue;

            long use = Math.min(operations, count);
            freq[d] -= use;
            freq[d - 1] += use;
            operations -= use;
        }

        long ans = 0;

        for (int d = 1; d <= 100000; d++) {
            ans += freq[d] * d * d;
        }

        return ans;
    }
}