class Solution:
    def stoneGameIX(self, stones: List[int]) -> bool:
        cnt = [0, 0, 0]

        for x in stones:
            cnt[x % 3] += 1

        # Stones divisible by 3 do not change the sum modulo 3.
        # If there are an even number of them, Alice can win only
        # when both remainder groups exist.
        if cnt[1] == 0 and cnt[2] == 0:
            return False

        if cnt[0] % 2 == 0:
            return cnt[1] > 0 and cnt[2] > 0

        # Odd number of remainder-0 stones.
        # Alice needs to start with the larger remainder group.
        return abs(cnt[1] - cnt[2]) > 2