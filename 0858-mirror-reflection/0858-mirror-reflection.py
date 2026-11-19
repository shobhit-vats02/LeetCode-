class Solution:
    def mirrorReflection(self, p: int, q: int) -> int:
        nok = lcm(p, q)
        return 2 if nok // q % 2 == 0 else 0 if nok // p % 2 == 0 else 1