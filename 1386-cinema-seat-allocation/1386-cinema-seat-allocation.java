import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();

        // Store reserved seats as a bitmask for each affected row
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(row, map.getOrDefault(row, 0) | (1 << col));
        }

        // Every completely empty row can fit 2 groups
        long ans = 2L * n;

        for (int mask : map.values()) {
            boolean left = true;   // seats 2,3,4,5
            boolean middle = true; // seats 4,5,6,7
            boolean right = true;  // seats 6,7,8,9

            for (int seat = 2; seat <= 5; seat++) {
                if ((mask & (1 << seat)) != 0) {
                    left = false;
                    break;
                }
            }

            for (int seat = 4; seat <= 7; seat++) {
                if ((mask & (1 << seat)) != 0) {
                    middle = false;
                    break;
                }
            }

            for (int seat = 6; seat <= 9; seat++) {
                if ((mask & (1 << seat)) != 0) {
                    right = false;
                    break;
                }
            }

            // This row initially contributed 2.
            // Determine how many groups it can actually hold.
            if (left && right) {
                // Can use both non-overlapping blocks
                // 2-5 and 6-9
            } else if (left || middle || right) {
                // At least one block is possible
                ans -= 1;
            } else {
                // No block is possible
                ans -= 2;
            }
        }

        return (int) ans;
    }
}