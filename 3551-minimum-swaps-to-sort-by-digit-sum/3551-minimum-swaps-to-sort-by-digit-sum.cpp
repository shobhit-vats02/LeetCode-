class Solution {
public:
    int minSwaps(vector<int>& nums) {
        int n = nums.size();
        vector<pair<int, int>> arr;

        for (int x : nums) {
            int sum = 0, temp = x;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            arr.push_back({sum, x});
        }

        sort(arr.begin(), arr.end());

        unordered_map<int, int> pos;
        for (int i = 0; i < n; i++) {
            pos[nums[i]] = i;
        }

        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == arr[i].second) continue;

            int j = pos[arr[i].second];
            pos[nums[i]] = j;
            pos[nums[j]] = i;
            swap(nums[i], nums[j]);
            swaps++;
        }

        return swaps;
    }
};