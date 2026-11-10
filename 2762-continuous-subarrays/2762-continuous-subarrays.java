import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        long totalSubarrays = 0;
        int left = 0;
        
        // Deques to store indices of max and min elements in the current window
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();
        
        for (int right = 0; right < n; right++) {
            // Maintain decreasing order in maxDeque
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(right);
            
            // Maintain increasing order in minDeque
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.addLast(right);
            
            // If the current window is invalid (max - min > 2), shrink it from the left
            while (!maxDeque.isEmpty() && !minDeque.isEmpty() && 
                   nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > 2) {
                
                // Slide the left pointer up
                left++;
                
                // Remove indices that fall outside the window boundaries
                if (maxDeque.peekFirst() < left) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() < left) {
                    minDeque.pollFirst();
                }
            }
            
            // The number of valid subarrays ending at the 'right' index is (right - left + 1)
            totalSubarrays += (right - left + 1);
        }
        
        return totalSubarrays;
    }
}