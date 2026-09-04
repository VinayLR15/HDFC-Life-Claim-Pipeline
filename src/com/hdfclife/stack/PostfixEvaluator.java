package com.hdfclife.stack;

public final class PostfixEvaluator {

    private PostfixEvaluator() {
    }

    public static int evaluate(
            String expression) {

        LinkedClaimStack stack =
                new LinkedClaimStack();

        String[] tokens =
                expression.trim().split(" ");

        for (int i = 0; i < tokens.length; i++) {

            String token = tokens[i];

            if (isOperator(token)) {

                int right = stack.pop();
                int left = stack.pop();

                stack.push(apply(left, right, token.charAt(0)));

            } else {

                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private static boolean isOperator(String token) {

        return token.length() == 1 && "+-*/".indexOf(token.charAt(0)) >= 0;
    }

    private static int apply(int left, int right, char operator) {

        switch (operator) {

            case '+':
                return left + right;

            case '-':
                return left - right;

            case '*':
                return left * right;

            case '/':

                if (right == 0) {
                    throw new ArithmeticException(
                            "Division by zero"
                    );
                }

                return left / right;

            default:
                throw new IllegalArgumentException(
                        "Unknown operator: " + operator
                );
        }
    }
}