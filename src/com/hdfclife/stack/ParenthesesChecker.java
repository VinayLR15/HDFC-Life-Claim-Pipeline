package com.hdfclife.stack;

public final class ParenthesesChecker {
    private ParenthesesChecker() {}

    public static boolean isBalanced(String input) {
        ClaimStack stack = new ArrayClaimStack();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                char opening = (char) stack.pop();
                if (!matches(opening, c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean matches(char opening, char closing) {
        return (opening == '(' && closing == ')')
                || (opening == '[' && closing == ']')
                || (opening == '{' && closing == '}');
    }
}
