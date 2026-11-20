class Solution {
public:

    int gcd(int a, int b) {
        while(a > 0 && b > 0) {
            if(a > b) {
                a %= b;
            } else {
                b %= a;
            }
        }

        if(a == 0) return b;
        if (b == 0) return a;
        return 0;
    }

    bool hasGroupsSizeX(vector<int>& deck) {

        int n = deck.size();
        unordered_map<int, int> mp;

        for(int i = 0; i < n; i++) {
            mp[deck[i]]++;
        }

        int g = 0;

        for(auto it : mp) {
            g = gcd(g, it.second);
        }

        return g > 1;
    }
};