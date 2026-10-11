class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        return solve(expression, new java.util.HashMap<>());
    }

    private List<Integer> solve(String expression,
                                java.util.Map<String, List<Integer>> memo) {
        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }

        List<Integer> result = new java.util.ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            char op = expression.charAt(i);

            if (op == '+' || op == '-' || op == '*') {
                List<Integer> left = solve(expression.substring(0, i), memo);
                List<Integer> right = solve(expression.substring(i + 1), memo);

                for (int a : left) {
                    for (int b : right) {
                        if (op == '+') {
                            result.add(a + b);
                        } else if (op == '-') {
                            result.add(a - b);
                        } else {
                            result.add(a * b);
                        }
                    }
                }
            }
        }

        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression));
        }

        memo.put(expression, result);
        return result;
    }
}