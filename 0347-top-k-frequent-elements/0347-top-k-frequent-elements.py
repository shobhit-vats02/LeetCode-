import heapq

class Solution:
    def topKFrequent(self, nums, k):
        freq = {}

        for num in nums:
            freq[num] = freq.get(num, 0) + 1

        pq = []

        for num, count in freq.items():

            if len(pq) < k:
                heapq.heappush(pq, (count, num))
            else:
                heapq.heappush(pq, (count, num))
                heapq.heappop(pq)

        result = []

        while pq:
            result.append(heapq.heappop(pq)[1])

        return result