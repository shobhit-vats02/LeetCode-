class Solution { 
    public int[] findErrorNums(int[] nums) { 
        int n = nums.length;
        // count array to store frequencies of numbers 1 to n
        int[] count = new int[n + 1]; 
        int[] arr = new int[2]; 
        
        // Count frequencies of each number
        for (int num : nums) {
            count[num]++;
        }
        
        // Find the duplicate (count == 2) and missing (count == 0)
        for (int i = 1; i <= n; i++) {
            if (count[i] == 2) {
                arr[0] = i; // Duplicate
            } else if (count[i] == 0) {
                arr[1] = i; // Missing
            }
        }
        
        return arr; 
    } 
}
