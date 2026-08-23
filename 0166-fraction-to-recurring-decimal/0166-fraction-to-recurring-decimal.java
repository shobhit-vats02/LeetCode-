class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        StringBuilder ans = new StringBuilder();

        // Handle sign
        if ((numerator < 0) ^ (denominator < 0)) {
            ans.append("-");
        }

        // Use long to avoid overflow for Integer.MIN_VALUE
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Integer part
        ans.append(num / den);

        long remainder = num % den;

        // No fractional part
        if (remainder == 0) {
            return ans.toString();
        }

        ans.append(".");

        // remainder -> position in answer
        HashMap<Long, Integer> map = new HashMap<>();

        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                int pos = map.get(remainder);
                ans.insert(pos, "(");
                ans.append(")");
                break;
            }

            map.put(remainder, ans.length());

            remainder *= 10;
            ans.append(remainder / den);
            remainder %= den;
        }

        return ans.toString();
    }
}