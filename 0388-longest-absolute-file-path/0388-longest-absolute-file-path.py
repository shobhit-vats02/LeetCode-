class Solution(object):
    def lengthLongestPath(self, input):
        lines = input.split('\n')
        d = {0: 0}
        ans = 0
        for line in lines:
            level = line.count('\t')
            name = line.lstrip('\t')
            if '.' in name:
                ans = max(ans, d[level] + len(name))
            else:
                d[level + 1] = d[level] + len(name) + 1
        return ans